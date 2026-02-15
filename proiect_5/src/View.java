import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class View extends JFrame {

    private Spital spital;

    private JTextField nume;
    private JTextField cnp;
    private JTextField sectie;
    private JTextField specializare;
    private JTextField codParafa;

    private JTextField cnpPacient;
    private JTextField codParafaProg;
    private JTextField durata;
    private JTextField tipConsultatie;

    private JTextArea output;

    public View(Spital spital) {
        this.spital = spital;

        setTitle("Sistem Spital - View Simplu");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(5, 5));

        add(createFormPanel(), BorderLayout.CENTER);
        add(createOutputPanel(), BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }


    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Date"));

        panel.add(new JLabel("Nume medic"));
        nume = new JTextField();
        panel.add(nume);

        panel.add(new JLabel("CNP medic"));
        cnp = new JTextField();
        panel.add(cnp);

        panel.add(new JLabel("Sectie"));
        sectie = new JTextField();
        panel.add(sectie);

        panel.add(new JLabel("Specializare"));
        specializare = new JTextField();
        panel.add(specializare);

        panel.add(new JLabel("Cod parafa"));
        codParafa = new JTextField();
        panel.add(codParafa);

        JButton addMedic = new JButton("Adauga medic");
        panel.add(addMedic);
        panel.add(new JLabel(""));

        panel.add(new JLabel("CNP pacient"));
        cnpPacient = new JTextField();
        panel.add(cnpPacient);

        panel.add(new JLabel("Cod parafa medic"));
        codParafaProg = new JTextField();
        panel.add(codParafaProg);

        panel.add(new JLabel("Durata (minute)"));
        durata = new JTextField();
        panel.add(durata);

        panel.add(new JLabel("Tip consultatie"));
        tipConsultatie = new JTextField();
        panel.add(tipConsultatie);

        JButton addProgramare = new JButton("Creeaza programare");
        JButton showPersonal = new JButton("Afiseaza personal");
        JButton showProgram = new JButton("Afiseaza program medic");

        panel.add(addProgramare);
        panel.add(showPersonal);
        panel.add(showProgram);
        panel.add(new JLabel(""));

        addMedic.addActionListener(e -> {
            Medic medic = new Medic(
                    nume.getText(),
                    cnp.getText(),
                    sectie.getText(),
                    specializare.getText(),
                    codParafa.getText()
            );
            spital.inregistreazaMedic(medic);
            output.setText("Medic adaugat.\n");
        });

        showPersonal.addActionListener(e -> {
            output.setText("");
            spital.afiseazaPersonalMedical(output);
        });

        addProgramare.addActionListener(e -> {
            try {
                Medic medic = spital.gasesteMedicDupaCodParafa(codParafaProg.getText());
                Pacient pacient = spital.cautaPacientDupaCNP(cnpPacient.getText());

                if (medic == null || pacient == null) {
                    output.setText("Medic sau pacient inexistent.\n");
                    return;
                }

                int durataMin = Integer.parseInt(durata.getText());

                spital.creeazaProgramare(
                        medic,
                        pacient,
                        new Date(),
                        durataMin,
                        tipConsultatie.getText()
                );

                output.setText("Programare creata.\n");

            } catch (MedicIndisponibilException ex) {
                output.setText("Medic indisponibil.\n");
            } catch (NumberFormatException ex) {
                output.setText("Durata invalida.\n");
            }
        });

        showProgram.addActionListener(e -> {
            output.setText("");
            spital.afiseazaProgramMedic(codParafaProg.getText(), output);
        });

        return panel;
    }

    private JScrollPane createOutputPanel() {
        output = new JTextArea();
        output.setEditable(false);

        JScrollPane scroll = new JScrollPane(output);
        scroll.setPreferredSize(new Dimension(800, 200));
        scroll.setBorder(BorderFactory.createTitledBorder("Output"));

        return scroll;
    }
}
