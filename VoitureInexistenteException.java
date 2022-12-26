public class VoitureInexistenteException extends Exception{
    @Override
    public String toString() {
        return "VoitureInexistenteException{Aucune voiture dans l'agence ne satisfait ces critetes}";
    }
}
