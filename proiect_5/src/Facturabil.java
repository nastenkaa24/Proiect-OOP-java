public interface Facturabil {

    Decont calculeazaDecontConsultatie(Pacient pacient, double tarif);

    Decont calculeazaDecontInternare(Pacient pacient, double costTotal);
}
