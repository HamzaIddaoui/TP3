import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    static void MenuLocation(Scanner sc,Agence agence){
        int choix;
       while(true){
           System.out.println("0/ Revenir menu principal");
           System.out.println("1/ Afficher les voitures louees ");
           System.out.println("2/ Ajouter une location a un client");
           System.out.println("3/ Afficher les locations");
           choix = sc.nextInt();
           switch (choix) {
               case 0 : return;
               case 1 : agence.AfficherVoituresLouees();
                   break;
               case 2 :
                   try {
                       ArrayList<Voiture> voitures = new ArrayList<>();
                       // Saisit des criteres de recherche de la voiture a louee
                       InterCritere critere = agence.SaisirCriteresVoitures(sc);
                       Iterator it = agence.Selectionne(critere,voitures);
                       if(!it.hasNext()) throw new VoitureInexistenteException();
                       System.out.println("Saisir donnees du client : ");
                       Client client = agence.SaisirClient(sc);
                       agence.loueVoiture(client,(Voiture)it.next());
                   } catch (VoitureInexistenteException e ) {
                       System.out.println(e);
                   } catch(VoitureEstLoueeException e) {
                       System.out.println(e);
                   } catch(ClientEstLoueurException e) {
                       System.out.println(e);
                   }
                   break;
               case 3 : agence.AfficherLocation();
                       break;
           }
       }
    }
    public static void main(String[] args) {
        Agence agence = new Agence();
        agence.AjouterVoiture(new Voiture("Renault","model1",2009,100));
        agence.AjouterVoiture(new Voiture("Renault","model2",2009,99));
        agence.AjouterVoiture(new Voiture("Renault","model3",2009,50));
        agence.AjouterVoiture(new Voiture("Renault","model1",2008,99));
        agence.AjouterVoiture(new Voiture("Renault1","model1",2009,99));
        Scanner sc = new Scanner(System.in);
        String marque;
        String nom;
        int annee,prix,choix;
        while(true){
            System.out.println("**************** Menu Agence ****************");
            System.out.println("0/ Quitter  ");
            System.out.println("1/ Ajouter une voiture  ");
            System.out.println("2/ Selectionner selon prix");
            System.out.println("3/ Selectionner selon marque");
            System.out.println("4/ Selectionner les voitures dont le prix < 100");
            System.out.println("5/ Selectionner selon les criteres suivant : " +
                                   " Marque = Renault, Annee de production = 2009, Prix < 100");
            System.out.println("6/ Gestion des locations");
            choix = sc.nextInt();
            switch (choix) {
                case 0 : System.exit(1);
                case 1 :
                         System.out.println("Saisir la marque : ");
                         marque = sc.next();
                         System.out.println("Saisir la nom : ");
                         nom = sc.next();
                         try {
                             System.out.println("Saisir l'annee : ");
                             annee = sc.nextInt();
                             System.out.println("Saisir le prix ");
                             prix = sc.nextInt();
                             if (prix <= 0) throw new PrixNegatif();
                             agence.AjouterVoiture(new Voiture(marque,nom,annee,prix));
                         } catch(NumberFormatException e ) {
                             System.out.println("Veuillez saisir un nombre pour annee/prix");
                         } catch (InputMismatchException e ) {
                             System.out.println("Format de saisit non respecte");
                         }catch(Exception e ) {
                             System.out.println(e);
                         }
                         break;
                case 2 :
                    try {
                        System.out.println("Saisir le prix ");
                        prix = sc.nextInt();
                        if (prix <= 0) throw new PrixNegatif();
                        agence.AfficherSelection(new CriterePrix(prix));
                    } catch (PrixNegatif e) {
                        System.out.println(e);
                    } catch (NumberFormatException e ) {
                        System.out.println("Veuillez saisir un nombre pour le prix.");
                    } catch (Exception e ) {
                        System.out.println(e);
                    }
                    break;
                case 3 :
                    System.out.println("Saisir la marque");
                    marque = sc.next();
                    agence.AfficherSelection(new CritereMarque(marque));
                    break;
                case 4 :
                    agence.AfficherSelection(new CriterePrix(100));
                    break;
                case 5 :
                    agence.AfficherSelection(
                                     new InterCritere(
                                             new CritereMarque("Renault"),
                                             new CriterePrix(100),
                                             new CritereAnnee(2009)
                                     )
                    );
                    break;
                case 6 :
                        System.out.println("************ Menu de gestion des locations ************");
                        MenuLocation(sc,agence);
                    break;

            }
            }
        }
    }
