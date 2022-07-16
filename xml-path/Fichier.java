public class Fichier extends Composant {

    public Fichier(String nom, int niveau) {
        super(nom, niveau);
    }

    public void afficher() {
        int niveau = getNiveau();
        for (int i = 0; i < niveau; i++) {
            System.out.print("│\t");
        }
        System.out.println("├──" + getNom());
    }
}
