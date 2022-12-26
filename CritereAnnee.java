public class CritereAnnee implements Critere{

    private int annee;

    public CritereAnnee(int annee) {
        this.annee = annee;
    }

    @Override
    public boolean estSatisfaitPar(Voiture v) {
        if(v.getAnnee() == this.annee) return true;
        return false;
    }
}
