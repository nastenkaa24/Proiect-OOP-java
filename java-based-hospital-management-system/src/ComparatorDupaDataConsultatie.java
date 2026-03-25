import java.util.Comparator;

public class ComparatorDupaDataConsultatie implements Comparator<Consultatie> {

    @Override
    public int compare(Consultatie c1, Consultatie c2) {
        return c1.getData().compareTo(c2.getData());
    }
}
