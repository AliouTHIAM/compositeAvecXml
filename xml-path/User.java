import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import java.io.*;

public class User {
    private static int niveau;

    public static String pathToXml(String path) {

        File directory = new File(path);
        String xmlstring = "\n" + "<directory name = " + "\"" + directory.getName() + "\"" + ">";
        File[] liste = directory.listFiles();
        for (File df : liste) {
            if (df.isFile()) {
                xmlstring = xmlstring + "\n" + "\t" + "<file name = " + "\"" + df.getName() + "\"" + "/>";
            } else if (df.isDirectory()) {
                xmlstring = xmlstring + pathToXml(df.getAbsolutePath());
            }
        }

        xmlstring = xmlstring + "\n" + "</directory>" + "\n";
        return xmlstring;

    }

    public static Composant insertion(Element e) {
        Dossier home = new Dossier(e.getAttribute("name"), niveau);
        NodeList nodes = e.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element el = (Element) nodes.item(i);
                if (el.getNodeName().equals("file")) {
                    Composant myFile;
                    myFile = new Fichier(el.getAttribute("name"), home.getNiveau() + 1);
                    home.ajouter(myFile);
                } else if (el.getNodeName().equals("directory")) {
                    niveau = home.getNiveau() + 1;
                    home.ajouter(insertion(el));
                    niveau--;
                }
            }
        }
        return home;
    }

    public static Composant xmlToDoc(String xmlstring) throws ParserConfigurationException, SAXException, IOException {
        String xmlStr = "<?xml version=\"1.0\"?>" + xmlstring;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        StringBuilder xmlStringBuilder = new StringBuilder();
        xmlStringBuilder.append(xmlStr);
        ByteArrayInputStream input = new ByteArrayInputStream(xmlStringBuilder.toString().getBytes("UTF-8"));
        Document doc = builder.parse(input);
        Element element = doc.getDocumentElement();
        return insertion(element);
    }

    /**
     * @param args
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

        
        //
        String chemin = pathToXml("/home/perepogba/Bureau/Material");
        System.out.println(chemin);


        String cheminXml = pathToXml("/home/perepogba/Bureau/Material");
        Composant root = xmlToDoc(cheminXml);
        root.afficher();
    }
}
