import java.util.*;

public class Agence {
    private ArrayList<Voiture> voitures;
    private TreeMap<Client,Voiture> Locations;

    public Agence() {
        this.Locations = new TreeMap<>();
        this.voitures = new ArrayList<>();
    };
    public Agence(ArrayList<Voiture> voitures) {
        this.Locations = new TreeMap<>();
        this.voitures = voitures;
    }
    public Agence(Voiture ... args)
    {
        this.Locations = new TreeMap<>();
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
        System.out.println("Location ajoute avec succes");
    }

    public void rendVoiture(Client client) throws ClientNonLoueurException {
        if(this.Locations.remove(client) == null) throw new ClientNonLoueurException();
        System.out.println("Location a ete rendue avec succes");
    }

    public Iterator lesVoituresLouees() {
        Collection<Voiture> voitureslouees =  this.Locations.values();
        Iterator it = voitureslouees.iterator();
        return it;
    }

    public void AfficherVoituresLouees() {
        Iterator it = this.lesVoituresLouees();
        // Si aucune voiture n'est louee
        if (it == null) {
            System.out.println("Aucune voiture n'est louee.");
            return;
        }
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

    public Client SaisirClient(Scanner sc) {
        String nom,prenom,cin;
        Civilite civ = Civilite.Mr;
        boolean saisit = true;
        System.out.println("Saisir le nom : ");
        nom = sc.next();
        System.out.println("Saisir le prenom : ");
        prenom = sc.next();
        System.out.println("Saisir CIN : ");
        cin = sc.next();
        while(saisit){
            System.out.println("Saisir la civilite : (Tapez mr ou mme ou mlle) ");
            String civilite = sc.next();
            switch(civilite){
                case "mr" : civ = Civilite.Mr; saisit = false;break;
                case "mme" : civ = Civilite.Mme; saisit = false; break;
                case "mlle" : civ = Civilite.Mlle; saisit = false; break;
                default : System.out.println("Incorrect saisit !");
            }
        }
        Client client = new Client(nom,prenom,cin,civ);
        return client;
    }


    public void AfficherLocation(){
        int indice = 1;
        Iterator it = this.Locations.entrySet().iterator();
        if(!it.hasNext()){
            System.out.println("Aucune location n'est fait.");
            return;
        }
        while(it.hasNext()){
            Map.Entry location = (Map.Entry) it.next();
            System.out.println("Location numero : " + indice);
            System.out.println(location.getKey());
            System.out.println(location.getValue());
            indice++;
        }
    }

    @Override
    public String toString() {
        return "Agence{" +
                "voitures=" + voitures +
                '}';
    }
}
