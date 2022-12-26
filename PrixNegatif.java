public class PrixNegatif extends Exception{
    @Override
    public String toString() {
        return "PrixNegatif{ Le prix ne peut pas etre negatif ou nul }";
    }
}
