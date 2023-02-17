package ui;

import model.Autista;
import model.ShuttleLive;
import model.Veicolo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RegistraVeicolo extends javax.swing.JFrame {
    private ShuttleLive shuttlelive;
    private Autista autista;
    private JButton registraVeicoloButton;
    private JTextField targa;
    private JTextField marca;
    private JTextField modello;
    private JTextField colore;
    private JTextField n_posti;
    private JPanel veicoloPanel;
    private JButton tornaAlMenuPrincipaleButton;

    public RegistraVeicolo(ShuttleLive sl, Autista autist) {
        this.autista=autist;
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
                    Veicolo veicolo = new Veicolo(targa.getText(),autista.getUsername(), marca.getText(),modello.getText(),colore.getText(), Integer.valueOf(n_posti.getText()));
                    Map<String,Veicolo> veicoli = autista.getVeicoli();
                    System.out.println(veicoli);
                    veicoli.put(veicolo.getTarga(),veicolo);
                    System.out.println(veicoli);
                    shuttlelive.inserisciVeicolo(veicolo);
                    autista.setVeicoli(veicoli);
                    new MenuAutista(shuttlelive,autista);
                } catch (Exception ex) {
                    new RegistraVeicolo(shuttlelive,autista);
                }
                setVisible(false);
            }
        });
        tornaAlMenuPrincipaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new MenuAutista(shuttlelive,autista);
                setVisible(false);

            }
        });
}
}
