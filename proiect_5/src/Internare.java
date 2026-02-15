import java.util.Date;

public class Internare {
    private Pacient pacient;
    private String sectie;
    private String salon;
    private Date data;

    private int zileSpitalizare;
    private String[] proceduri;
    private String[] investigatii;

    private Date dataExternare;
    private boolean externat;
    private double costTotal;

    public Internare(Pacient pacient, String sectie, String salon, Date data) {
        this.pacient = pacient;
        this.sectie = sectie;
        this.salon = salon;
        this.data = data;

        this.zileSpitalizare = 0;
        this.proceduri = new String[0];
        this.investigatii = new String[0];

        this.dataExternare = null;
        this.externat = false;
        this.costTotal = 0;
    }

    public Pacient getPacient() {
        return pacient;
    }

    public String getSectie() {
        return sectie;
    }

    public String getSalon() {
        return salon;
    }

    public Date getData() {
        return data;
    }

    public int getZileSpitalizare() {
        return zileSpitalizare;
    }

    public void setZileSpitalizare(int zileSpitalizare) {
        this.zileSpitalizare = zileSpitalizare;
    }

    public String[] getProceduri() {
        return proceduri;
    }

    public void setProceduri(String[] proceduri) {
        this.proceduri = proceduri;
    }

    public String[] getInvestigatii() {
        return investigatii;
    }

    public void setInvestigatii(String[] investigatii) {
        this.investigatii = investigatii;
    }

    public Date getDataExternare() {
        return dataExternare;
    }

    public void setDataExternare(Date dataExternare) {
        this.dataExternare = dataExternare;
    }

    public boolean isExternat() {
        return externat;
    }

    public void setExternat(boolean externat) {
        this.externat = externat;
    }

    public double getCostTotal() {
        return costTotal;
    }

    public void setCostTotal(double costTotal) {
        this.costTotal = costTotal;
    }

    public void afisareProceduri() {
        System.out.print("[ ");
        String[] proc = this.proceduri;
        for (int i = 0; i < proc.length; i++) {
            System.out.print(proc[i]);
        }
        System.out.println(" ]");
    }

    public void afisareInvestigati() {
        System.out.print("[ ");
        String[] proc = this.investigatii;
        for (int i = 0; i < proc.length; i++) {
            System.out.print(proc[i]);
        }
        System.out.println(" ]");
    }

    public int zilePanaLaExternare(Date dataExternare) {
        long ms = dataExternare.getTime() - data.getTime();
        int zile = (int) (ms / (1000L * 60L * 60L * 24L));
        if (zile <= 0) {
            zile = 1;
        }
        return zile;
    }

}
