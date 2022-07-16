import java.util.ArrayList;

public class Dossier extends Composant {

    ArrayList<Composant> composants = new ArrayList<Composant>();

    public Dossier(String nom, int niveau) {
        super(nom, niveau);
    }

    public void ajouter(Composant c) {
        composants.add(c);
    }

    public void afficher() {

        int niveau = getNiveau();
        for (int i = 0; i < niveau; i++) {
            System.out.print("│\t");
        }
        System.out.println("├──" + getNom());
        for (Composant c : composants) {
            c.afficher();
        }
    }
}
