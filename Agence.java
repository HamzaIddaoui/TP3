import java.util.*;

public class Agence {
    private ArrayList<Voiture> voitures;
    private Map<Client,Voiture> Locations;

    public Agence() {voitures = new ArrayList<>();};
    public Agence(ArrayList<Voiture> voitures) {
        this.voitures = voitures;
    }
    public Agence(Voiture ... args)
    {
        voitures = new ArrayList<>();
        for ( Voiture v : args) voitures.add(v);
    }

    public void AjouterVoiture(Voiture voiture) {
        this.voitures.add(voiture);
    }

    public Iterator Selectionne(Critere c, ArrayList<Voiture> v)
    {
        // Parcourir l'ensemble des voitures, si le critere est satistfait, ajouter a la collection
        for ( Voiture voiture : this.voitures)
            // Interface critere peut etre instanciee par n'importe qu'elle classe qu'elle l'implemente
            if(c.estSatisfaitPar(voiture))  v.add(voiture);
        Iterator iterator = v.iterator();
        return iterator;
    }

    public void AfficherSelection(Critere c) {
        ArrayList<Voiture> list = new ArrayList<>();
        Iterator iterator = Selectionne(c,list);
        // Si Iterator n'a pas de suivant (list est vide)
        if(!iterator.hasNext())
            System.out.println("Aucune voiture ne satisfait ce critere.");
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public boolean estLoeur(Client client) {
        if(this.Locations.containsKey(client)) return true;
        return false;
    }

    public boolean estLouee(Voiture voiture) {
        if(this.Locations.containsValue(voiture)) return true;
        return false;
    }

    public void loueVoiture(Client client, Voiture voiture) throws ClientEstLoueurException, VoitureEstLoueeException {
        if(estLoeur(client)) throw new ClientEstLoueurException();
        if(estLouee(voiture)) throw new VoitureEstLoueeException();
        this.Locations.put(client,voiture);
        System.out.println("Location ajoute avec success");
    }

    public void rendVoiture(Client client) throws ClientNonLoueurException {
        if(this.Locations.remove(client) == null) throw new ClientNonLoueurException();
        System.out.println("Location a ete rendue avec succes");
    }

    public Iterator lesVoituresLouees() {
        ArrayList<Voiture> voitureslouees = new ArrayList<>();
        voitureslouees = (ArrayList<Voiture>) this.Locations.values();
        Iterator it = voitureslouees.iterator();
        return it;
    }

    public void AfficherVoituresLouees() {
        Iterator it = this.lesVoituresLouees();
        while(it.hasNext())
            System.out.println(it.next());
    }

    public InterCritere SaisirCriteresVoitures(Scanner sc) {
        String marque;
        int prix,annee;
        InterCritere critere = null;
        System.out.println("Saisir la marque : ");
        marque = sc.next();
        try {
            System.out.println("Saisir l'annee : ");
            annee = sc.nextInt();
            System.out.println("Saisir le prix ");
            prix = sc.nextInt();
            if (prix <= 0) throw new PrixNegatif();
            critere = new InterCritere(
                    new CriterePrix(prix),
                    new CritereMarque(marque),
                    new CritereAnnee(annee)
            );
        } catch(NumberFormatException e ) {
            System.out.println("Veuillez saisir un nombre pour annee/prix");
        } catch (InputMismatchException e ) {
            System.out.println("Format de saisit non respecte");
        }catch(Exception e ) {
            System.out.println(e);
        }
        return critere;
    }

    public void SaisirClient(Scanner sc) {
        /**
        String nom,prenom,cin;
        Civilite civ;
        System.out.println("Saisir le nom : ");
        nom = sc.next();
         **/
    }

    @Override
    public String toString() {
        return "Agence{" +
                "voitures=" + voitures +
                '}';
    }
}
