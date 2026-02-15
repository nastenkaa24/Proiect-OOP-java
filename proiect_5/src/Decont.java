public class Decont {
    private double sumaPacient;
    private double sumaCNAS;

    public Decont(double sumaPacient, double sumaCNAS) {
        this.sumaPacient = sumaPacient;
        this.sumaCNAS = sumaCNAS;
    }

    public double getSumaPacient() {
        return sumaPacient;
    }

    public double getSumaCNAS() {
        return sumaCNAS;
    }

    public double getTotal() {
        return sumaPacient + sumaCNAS;
    }
}
