public abstract class Composant {

    private int niveau;
    private String nom;

    public Composant(String nom, int niveau) {
        this.nom = nom;
        this.niveau = niveau;
    }

    public abstract void afficher();

    public String getNom() {
        return this.nom;
    }

    public int getNiveau() {
        return this.niveau;
    }
}
