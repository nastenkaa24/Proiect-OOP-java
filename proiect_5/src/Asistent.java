public class Asistent extends PersonalMedical {
    private NivelAsistent nivel;
    private Ture[] ture;

    public Asistent(String nume1, String cnp1, String sectie1,
                    NivelAsistent nivel1, Ture[] ture1) {
        super(nume1, cnp1, sectie1);
        nivel = nivel1;
        ture = ture1;
    }

    public NivelAsistent getNivel() {
        return nivel;
    }

    public void setNivel(NivelAsistent nivel) {
        this.nivel = nivel;
    }

    public Ture[] getTure() {
        return ture;
    }

    public void setTure(Ture[] ture) {
        this.ture = ture;
    }
}
