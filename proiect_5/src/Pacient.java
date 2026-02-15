public class Pacient implements Comparable<Pacient> {
    private String nume;
    private String cnp;
    private boolean asigurat;
    private String casaAsigurari;
    private String adresa;
    private String telefon;
    private String medicDeFamilie;


    public Pacient(String nume1, String cnp1, boolean asigurat1, String casaAsigurari1) {
        nume = nume1;
        cnp = cnp1;
        asigurat = asigurat1;
        casaAsigurari = casaAsigurari1;
        adresa = "";
        telefon = "";
        medicDeFamilie = "";
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public boolean isAsigurat() {
        return asigurat;
    }

    public void setAsigurat(boolean asigurat) {
        this.asigurat = asigurat;
    }

    public String getCasaAsigurari() {
        return casaAsigurari;
    }

    public void setCasaAsigurari(String casaAsigurari) {
        this.casaAsigurari = casaAsigurari;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getMedicDeFamilie() {
        return medicDeFamilie;
    }

    public void setMedicDeFamilie(String medicDeFamilie) {
        this.medicDeFamilie = medicDeFamilie;
    }

    @Override
    public int compareTo(Pacient p) {
        return this.nume.compareTo(p.nume);
    }
}
