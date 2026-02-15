import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Spital implements Programabil, Facturabil {
    private ArrayList<PersonalMedical> personalMedical;
    private ArrayList<Programare> programari;
    private HashMap<String, Pacient> pacienti;
    private ArrayList<Consultatie> consultatii;
    private ArrayList<Internare> internari;

    public Spital() {
        personalMedical = new ArrayList<>();
        programari = new ArrayList<>();
        pacienti = new HashMap<>();
        consultatii = new ArrayList<>();
        internari = new ArrayList<>();

    }

    public ArrayList<PersonalMedical> getPersonalMedical() {
        return personalMedical;
    }

    public void inregistreazaMedic(Medic medic) {
        if (medic != null) {
            personalMedical.add(medic);
        }
    }

    public void inregistreazaAsistent(Asistent asistent) {
        if (asistent != null) {
            personalMedical.add(asistent);
        }
    }

    public void afiseazaPersonalMedical(JTextArea area) {
        for (int i = 0; i < personalMedical.size(); i++) {
            PersonalMedical p = personalMedical.get(i);
            area.append(p.getNume() + " - " + p.getSectie() + "\n");
        }
    }

    public void afiseazaPersonalMedical() {
        if (personalMedical.size() == 0) {
            System.out.println("Nu exista personal medical inregistrat.");
            return;
        }

        for (int i = 0; i < personalMedical.size(); i++) {
            PersonalMedical p = personalMedical.get(i);

            if (p instanceof Medic) {
                Medic m = (Medic) p;
                System.out.println("Medic: Nume: " + m.getNume() + ", CNP: " + m.getCnp() + ", Sectie: " + m.getSectie() + ", Specializare: " + m.getSpecializare() + ", Cod parafa: " + m.getCodParafa());
            } else if (p instanceof Asistent) {
                Asistent a = (Asistent) p;
                System.out.print("Asistent  Nume: " + a.getNume() + ", CNP: " + a.getCnp() + ", Sectie: " + a.getSectie() + ", Nivel: " + a.getNivel() + ", Ture: ");

                Ture[] ture = a.getTure();
                for (int j = 0; j < ture.length; j++) {
                    System.out.print(ture[j]);
                    if (j < ture.length - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println();
            }
        }
    }

    public void adaugaProgramare(Programare programare) {
        if (programare != null) {
            programari.add(programare);
        }
    }


    public Medic gasesteMedicDupaCodParafa(String codParafa) {
        for (int i = 0; i < personalMedical.size(); i++) {
            if (personalMedical.get(i) instanceof Medic) {
                Medic medic = (Medic) personalMedical.get(i);
                if (medic.getCodParafa().equals(codParafa)) {
                    return medic;
                }
            }
        }
        return null;
    }

    public void afiseazaProgramMedic(String codParafa, JTextArea area) {
        Medic medic = gasesteMedicDupaCodParafa(codParafa);

        if (medic == null) {
            area.append("Medic inexistent.\n");
            return;
        }

        for (int i = 0; i < programari.size(); i++) {
            Programare p = programari.get(i);
            if (p.getMedic().getCodParafa().equals(codParafa)) {
                area.append(
                        p.getData() + " - " +
                                p.getPacient().getNume() + " - " +
                                p.getTipConsultatie() + "\n"
                );
            }
        }
    }


    public void afiseazaProgramMedic(String codParafa) {
        Medic medic = gasesteMedicDupaCodParafa(codParafa);
        System.out.println("Program pentru medicul: " + medic.getNume());

        boolean areProgramari = false;

        for (int i = 0; i < programari.size(); i++) {
            Programare p = programari.get(i);

            if (p.getMedic().getCodParafa().equals(codParafa)) {
                areProgramari = true;
                System.out.println("Data: " + p.getData() + ", Durata: " + p.getDurataMinute() + " minute" + ", Tip: " + p.getTipConsultatie() + ", Pacient: " + p.getPacient().getNume());
            }
        }

        if (!areProgramari) {
            System.out.println("Medicul nu are programari.");
        }
    }


    public void inregistreazaPacient(Pacient pacient) {
        pacienti.put(pacient.getCnp(), pacient);
    }

    public void actualizeazaDatePacient(String cnp, String adresa, String telefon, String medicDeFamilie) {
        Pacient p = pacienti.get(cnp);
        if (p == null) {
            throw new PacientInexistentException(cnp + " NU EXISTA!");
        }

        p.setAdresa(adresa);
        p.setTelefon(telefon);
        p.setMedicDeFamilie(medicDeFamilie);
    }

    public void afiseazaFisaPacient(String cnp) {
        Pacient pacient1 = pacienti.get(cnp);
        if (pacient1 == null) {
            throw new PacientInexistentException(cnp + " NU EXISTA!");
        }

        System.out.println("Fisa pacient: ");
        System.out.println("  Nume: " + pacient1.getNume());
        System.out.println("  CNP: " + pacient1.getCnp());
        System.out.println("  Asigurat: " + pacient1.isAsigurat());
        System.out.println("  Casa asigurari: " + pacient1.getCasaAsigurari());
        System.out.println("  Adresa: " + pacient1.getAdresa());
        System.out.println("  Telefon: " + pacient1.getTelefon());
        System.out.println("  Medic de familie: " + pacient1.getMedicDeFamilie());

        System.out.println("  Istoric consultatii: (urmeaza dupa clasa Consultatie)");
        System.out.println("  Istoric internari: (urmeaza dupa clasa Internare)");
        System.out.println("  Istoric investigatii: (optional, daca faci si Investigatie)");
    }


    @Override
    public boolean esteMedicDisponibil(Medic medic, Date data) {
        return medic.esteDisponibil(data);
    }

    @Override
    public void creeazaProgramare(Medic medic, Pacient pacient, Date data, int durataMinute, String tipConsultatie) {
        if (this.esteMedicDisponibil(medic, data) == false) {
            throw new MedicIndisponibilException("Medicul nu este disponibil la " + data);
        }

        Programare programare = new Programare(medic, pacient, data, durataMinute, tipConsultatie);
        programari.add(programare);
        medic.adaugaProgramare(programare);
    }

    public void inregistreazaConsultatie(String codParafaMedic, String cnpPacient, Date data, String diagnostic, String recomandari, double tarif) {
        Pacient pacient = pacienti.get(cnpPacient);
        Medic medic = gasesteMedicDupaCodParafa(codParafaMedic);
        Consultatie consultatie = new Consultatie(medic, pacient, data, diagnostic, recomandari, tarif);
        consultatii.add(consultatie);
    }


    public void afiseazaConsultatiiPacient(String cnpPacient) {
        boolean exista = false;
        for (int i = 0; i < consultatii.size(); i++) {
            Consultatie c = consultatii.get(i);
            if (c.getPacient().getCnp().equals(cnpPacient)) {
                exista = true;
                System.out.println("Data: " + c.getData() + ", Medic: " + c.getMedic().getNume() + ", Diagnostic: " + c.getDiagnostic() + ", Recomandari: " + c.getRecomandari() + ", Tarif: " + c.getTarif());
            }
        }

        if (!exista) {
            System.out.println("Nu exista consultatii pentru pacientul cu CNP: " + cnpPacient);
        }
    }

    public void initiazaInternare(String cnpPacient, String sectie, String salon, Date data) {
        Pacient pacient = pacienti.get(cnpPacient);
        Internare i = new Internare(pacient, sectie, salon, data);
        internari.add(i);
    }

    Internare gasesteInternare(String cnpPacient, Date dataInternare) {
        for (int i = 0; i < internari.size(); i++) {
            Internare in = internari.get(i);

            if (in.getPacient().getCnp().equals(cnpPacient) && in.getData().equals(dataInternare)) {
                return in;
            }
        }
        return null;
    }

    public void inregistreazaDetaliiInternare(String cnpPacient, Date dataInternare, int zileSpitalizare, String[] proceduri, String[] investigatii) {

        Pacient pacient = pacienti.get(cnpPacient);
        if (pacient == null) {
            throw new PacientInexistentException(cnpPacient + " NU EXISTA!");
        }

        Internare internare = gasesteInternare(cnpPacient, dataInternare);
        if (internare == null) {
            System.out.println("Internare pentru pacientul cu CNP " + cnpPacient + " NU EXISTA!");
            return;
        }

        internare.setZileSpitalizare(zileSpitalizare);
        internare.setProceduri(proceduri);
        internare.setInvestigatii(investigatii);
    }

    public void afiseazaInternare(String cnpPacient, Date dataInternare) {
        Internare internare = gasesteInternare(cnpPacient, dataInternare);
        System.out.println("Internare");
        System.out.println("Pacient: " + internare.getPacient().getNume() + " (CNP: " + internare.getPacient().getCnp() + ")");
        System.out.println("Sectie: " + internare.getSectie());
        System.out.println("Salon: " + internare.getSalon());
        System.out.println("Data internare: " + internare.getData());
        System.out.println("Zile spitalizare: " + internare.getZileSpitalizare());

        internare.afisareProceduri();
        internare.afisareInvestigati();

        System.out.println("Externat: " + internare.isExternat());
        if (internare.isExternat()) {
            System.out.println("Data externare: " + internare.getDataExternare());
            System.out.println("Cost total: " + internare.getCostTotal());
        }
    }

    public Pacient cautaPacientDupaNume(String nume) {
        Object[] valori = pacienti.values().toArray();
        for (int i = 0; i < valori.length; i++) {
            Pacient p = (Pacient) valori[i];
            if (p.getNume().equals(nume)) {
                return p;
            }
        }
        return null;
    }


    public void finalizeazaExternare(String cnpPacient, Date dataInternare, Date dataExternare, double tarifPeZi) {

        Pacient pacient = pacienti.get(cnpPacient);
        Internare internare = gasesteInternare(cnpPacient, dataInternare);

        if (internare.isExternat()) {
            return;
        }

        int zile = internare.zilePanaLaExternare(dataExternare);

        double costTotal = zile * tarifPeZi;

        internare.setDataExternare(dataExternare);
        internare.setExternat(true);
        internare.setCostTotal(costTotal);

        System.out.println("Externare finalizata. Zile: " + zile + ", Cost total: " + costTotal);
    }


    @Override
    public Decont calculeazaDecontConsultatie(Pacient pacient, double tarif) {

        if (pacient.isAsigurat()) {
            return new Decont(0, tarif);
        }

        return new Decont(tarif, 0);
    }

    @Override
    public Decont calculeazaDecontInternare(Pacient pacient, double costTotal) {

        if (pacient.isAsigurat()) {
            double sumaCNAS = costTotal * Tarife.PROCENT_CNAS_INTERNARE;
            double sumaPacient = costTotal - sumaCNAS;
            return new Decont(sumaPacient, sumaCNAS);
        }

        return new Decont(costTotal, 0);
    }

    public Pacient cautaPacientDupaCNP(String cnp) {
        return pacienti.get(cnp);
    }
}
