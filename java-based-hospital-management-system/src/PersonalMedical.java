public abstract class PersonalMedical {
    protected String nume;
    protected String cnp;
    protected String sectie;

    PersonalMedical(String nume, String cnp, String sectie) {
        this.nume = nume;
        this.cnp = cnp;
        this.sectie = sectie;
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

    public String getSectie() {
        return sectie;
    }

    public void setSectie(String sectie) {
        this.sectie = sectie;
    }

}
