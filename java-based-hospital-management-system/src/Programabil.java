import java.util.Date;

public interface Programabil {
    boolean esteMedicDisponibil(Medic medic, Date data);
    void creeazaProgramare(Medic medic, Pacient pacient, Date data, int durataMinute, String tipConsultatie);
}