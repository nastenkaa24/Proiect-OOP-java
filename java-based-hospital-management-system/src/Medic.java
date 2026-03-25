import java.util.ArrayList;
import java.util.Date;

public class Medic extends PersonalMedical {
    private String specializare;
    private String codParafa;
    private ArrayList<Programare> programari;


    public Medic(String nume, String cnp, String sectie, String specializare1, String codParafa1) {
        super(nume, cnp, sectie);
        this.specializare = specializare1;
        this.codParafa = codParafa1;
        programari = new ArrayList<>();
    }

    public String getSpecializare() {
        return specializare;
    }

    public void setSpecializare(String specializare) {
        this.specializare = specializare;
    }

    public String getCodParafa() {
        return codParafa;
    }

    public void setCodParafa(String codParafa) {
        this.codParafa = codParafa;
    }

    public ArrayList<Programare> getProgramari() {
        return new ArrayList<>(programari);
    }

    public void adaugaProgramare(Programare programare) {
        if (programare != null) {
            programari.add(programare);
        }
    }

    public boolean esteDisponibil(Date data) {
        for (int i = 0; i < programari.size(); i++) {
            Programare p = programari.get(i);
            Date d1 = p.getData();
            Date d2 = data;
            if (d1.getYear() == d2.getYear() && d1.getMonth() == d2.getMonth() && d1.getDate() == d2.getDate() && d1.getHours() == d2.getHours()) {
                return false;
            }
        }
        return true;
    }


}
