import java.util.Date;

public class Programare {
    private Medic medic;
    private Pacient pacient;
    private Date data;
    private int durataMinute;
    private String tipConsultatie;

    public Programare(Medic medic1, Pacient pacient1, Date data1, int durataMinute1, String tipConsultatie1) {
        medic = medic1;
        pacient = pacient1;
        data = data1;
        durataMinute = durataMinute1;
        tipConsultatie = tipConsultatie1;
    }

    public Medic getMedic() {
        return medic;
    }

    public Pacient getPacient() {
        return pacient;
    }

    public Date getData() {
        return data;
    }

    public int getDurataMinute() {
        return durataMinute;
    }

    public String getTipConsultatie() {
        return tipConsultatie;
    }
}
