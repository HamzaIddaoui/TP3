import java.util.ArrayList;

public class InterCritere implements Critere{
    private ArrayList<Critere> lesCriteres;

    public InterCritere(Critere ... criteres) {
        this.lesCriteres = new ArrayList<>();
        for(Critere c : criteres)
            this.lesCriteres.add(c);
    };
    public void addCritere(Critere c ) {
        this.lesCriteres.add(c);
    }

    @Override
    public boolean estSatisfaitPar(Voiture v) {
        for (Critere critere : this.lesCriteres)
            if(!critere.estSatisfaitPar(v))
                return false;
        return true;
    }
}
