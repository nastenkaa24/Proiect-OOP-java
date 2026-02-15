import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Spital spital = new Spital();
        new View(spital);

        // Inregistrare medic/asistent
        Medic m1 = new Medic("Dr. Medic 2", "111111111111", "Cardiologie", "Cardiologie", "1111");
        Medic m2 = new Medic("Dr. Medic 1", "222222222222", "Radiologie", "Radiologie", "2222");

        Asistent a1 = new Asistent("Asistent 1", "33333333333", "Cardiologie", NivelAsistent.INCEPATOR, new Ture[]{Ture.ZI});
        Asistent a2 = new Asistent("Asistent 2", "44444444444", "Dermatologie", NivelAsistent.EXPERIMENTAT, new Ture[]{Ture.ZI, Ture.NOAPTE});

        spital.inregistreazaMedic(m1);
        spital.inregistreazaMedic(m2);
        System.out.println();

        spital.inregistreazaAsistent(a1);
        spital.inregistreazaAsistent(a2);

        // Afisare lista personalului medical
        System.out.println("Lista personal medical");
        spital.afiseazaPersonalMedical();

        System.out.println();

        // Vizualizare program de lucru pentru un medic
        spital.afiseazaProgramMedic("1111");

        System.out.println();

        // Inregistrare pacient nou
        Pacient p1 = new Pacient("Pacient 2", "55555555555", true, "CNAS");
        Pacient p2 = new Pacient("Pacient 1", "66666666666", false, null);

        spital.inregistreazaPacient(p1);
        spital.inregistreazaPacient(p2);

        try {
            Date dataProgramare = new Date();

            spital.creeazaProgramare(m1, p1, dataProgramare, 30, "Consultatie");
            spital.creeazaProgramare(m1, p2, dataProgramare, 20, "Control");

        } catch (MedicIndisponibilException e) {
            System.out.println("Exceptie: " + e.getMessage());
        }

        System.out.println();

        // Actualizare date pacient
        spital.afiseazaFisaPacient("55555555555");

        spital.actualizeazaDatePacient("55555555555", "Observator, nr 3", "07333222111", "Dr. Popescu");
        spital.actualizeazaDatePacient("66666666666", "Zorilor nr 5", "0799999999", "Dr. Ionescu");

        // Afisare fisa pacient
        spital.afiseazaFisaPacient("55555555555");

        System.out.println();

        // Creare programari
        Programare pr1 = new Programare(m1, p1, new Date(), 30, "Consultatie cardiologie");
        Programare pr2 = new Programare(m1, p2, new Date(), 20, "Control");
        Programare pr3 = new Programare(m2, p1, new Date(), 45, "Consultatie radiologie");

        spital.adaugaProgramare(pr1);
        spital.adaugaProgramare(pr2);
        spital.adaugaProgramare(pr3);

        // Vizualizare program de lucru pentru un medic (dupa programari)
        spital.afiseazaProgramMedic("1111");
        spital.afiseazaProgramMedic("2222");

        System.out.println();

        spital.inregistreazaConsultatie("1111", "55555555555", new Date(), "Gripa", "Paracetamol si Portocale", 200);
        spital.afiseazaConsultatiiPacient("55555555555");

        System.out.println();

        Date dataInternare = new Date();

        try {
            spital.initiazaInternare("55555555555", "Cardiologie", "Salon 3", dataInternare);
            spital.inregistreazaDetaliiInternare("55555555555", dataInternare, 7, new String[]{"Procedura 1", "Procedura 2"}, new String[]{"Investigatie 1", "Investigatie 2"});
        } catch (PacientInexistentException e) {
            System.out.println(e.getMessage());
        }
        spital.afiseazaInternare("55555555555", dataInternare);

        System.out.println();

        System.out.println("Pacienti sortati dupa nume:");
        ArrayList<Pacient> listaPacienti = new ArrayList<>();
        listaPacienti.add(p1);
        listaPacienti.add(p2);
        Collections.sort(listaPacienti);
        for (int i = 0; i < listaPacienti.size(); i++) {
            System.out.println("Pacient: " + listaPacienti.get(i).getNume());
        }

        System.out.println();

        System.out.println("Medici sortati dupa specializare: ");
        ArrayList<Medic> listaMedici = new ArrayList<>();
        listaMedici.add(m1);
        listaMedici.add(m2);
        Collections.sort(listaMedici, new ComparatorDupaSpecializare());
        for (int i = 0; i < listaMedici.size(); i++) {
            Medic m = listaMedici.get(i);
            System.out.println("Medic: " + m.getNume() + ", Specializare: " + m.getSpecializare());
        }

        System.out.println("\nFacturabil ");

        Decont d1 = spital.calculeazaDecontConsultatie(p1, Tarife.TARIF_CONSULTATIE);
        System.out.println("Consultatie asigurat: Pacient: " + d1.getSumaPacient() + ", CNAS: " + d1.getSumaCNAS());

        Decont d2 = spital.calculeazaDecontConsultatie(p2, Tarife.TARIF_CONSULTATIE);
        System.out.println("Consultatie neasigurat: Pacient: " + d2.getSumaPacient() + ", CNAS: " + d2.getSumaCNAS());

        Decont d3 = spital.calculeazaDecontInternare(p1, 4600);
        System.out.println("Internare asigurat: Pacient: " + d3.getSumaPacient() + ", CNAS: " + d3.getSumaCNAS());


    }
}
