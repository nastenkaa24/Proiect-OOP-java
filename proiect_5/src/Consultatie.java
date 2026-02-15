import java.util.Date;

public class Consultatie {
    private Medic medic;
    private Pacient pacient;
    private Date data;
    private String diagnostic;
    private String recomandari;
    private double tarif;

    public Consultatie(Medic medic, Pacient pacient, Date data, String diagnostic, String recomandari, double tarif) {
        this.medic = medic;
        this.pacient = pacient;
        this.data = data;
        this.diagnostic = diagnostic;
        this.recomandari = recomandari;
        this.tarif = tarif;
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

    public String getDiagnostic() {
        return diagnostic;
    }

    public String getRecomandari() {
        return recomandari;
    }

    public double getTarif() {
        return tarif;
    }
}
