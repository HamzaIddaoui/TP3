import java.util.Objects;

public class Voiture {
    private String marque;
    private String nom;
    private int annee;
    private int prix_location;

    public Voiture(String marque,String nom, int annee, int prix) {
        this.prix_location = prix;
        this.marque = marque;
        this.annee = annee;
        this.nom = nom;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public int getPrix_location() {
        return prix_location;
    }

    public void setPrix_location(int prix_location) {
        this.prix_location = prix_location;
    }

    public boolean equals(Voiture o) {
        if (this == o) return true;
        return  o.nom.equals(this.nom) &&
                o.marque.equals(this.marque)  &&
                o.annee == this.annee &&
                o.prix_location == this.prix_location;
    }

    @Override
    public String toString() {
        return "Voiture{" +
                "marque='" + marque + '\'' +
                ", nom='" + nom + '\'' +
                ", annee=" + annee +
                ", prix_location=" + prix_location +
                '}';
    }
}
