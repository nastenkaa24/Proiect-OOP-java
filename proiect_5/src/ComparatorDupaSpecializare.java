import java.util.Comparator;

public class ComparatorDupaSpecializare implements Comparator<Medic> {

    @Override
    public int compare(Medic m1, Medic m2) {
        return m1.getSpecializare().compareTo(m2.getSpecializare());
    }
}
