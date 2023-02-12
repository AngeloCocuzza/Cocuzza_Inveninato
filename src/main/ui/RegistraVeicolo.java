package ui;

import model.Autista;
import model.ShuttleLive;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistraVeicolo extends javax.swing.JFrame {
    private ShuttleLive shuttlelive;
    private JButton registraVeicoloButton;
    private JTextField targa;
    private JTextField marca;
    private JTextField modello;
    private JTextField colore;
    private JTextField n_posti;
    private JPanel veicoloPanel;
    public RegistraVeicolo(ShuttleLive sl, Autista autista) {
        this.shuttlelive=sl;
        setTitle("RegVeicolo");
        setContentPane(veicoloPanel);
        setSize(550,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        registraVeicoloButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    shuttlelive.inserisciVeicolo(targa.getText(),autista.getUsername(), marca.getText(),modello.getText(),colore.getText(), Integer.valueOf(n_posti.getText()));
                } catch (Exception ex) {
                    new RegistraVeicolo(shuttlelive,autista);
                }
                setVisible(false);
            }
        });
}
}
