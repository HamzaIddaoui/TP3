import java.util.Objects;

public class Client implements Comparable{
    private String nom;
    private String prenom;
    private String cin;
    private Civilite civilite;

    public Client(String nom, String prenom, String cin, Civilite civilite) {
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.civilite = civilite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        Client client = (Client) o;
        return Objects.equals(nom, client.nom)
                && Objects.equals(prenom, client.prenom)
                && Objects.equals(cin, client.cin)
                && civilite == client.civilite;
    }

    @Override
    public int compareTo(Object o) {
        return this.nom.compareTo(((Client)(o)).nom);
    }

    @Override
    public String toString() {
        return "Client{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", cin='" + cin + '\'' +
                ", civilite=" + civilite +
                '}';
    }
}
