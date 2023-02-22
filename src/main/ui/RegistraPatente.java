package ui;

import model.Autista;
import model.Patente;
import model.ShuttleLive;
import model.Veicolo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class RegistraPatente extends javax.swing.JFrame {
    private ShuttleLive shuttlelive;
    private Autista autista;
    private JButton bottoneConferma;
    private JTextField codice;
    private JTextField data_conseguimento;
    private JTextField data_scadenza;
    private JTextField livello;
    private JLabel cod;
    private JLabel dat;
    private JLabel data_s;
    private JLabel liv;
    private JPanel patentePanel;


    public RegistraPatente(ShuttleLive sl,Autista autist) {
        this.autista=autist;
        this.shuttlelive=sl;
        setTitle("RegPatente");
        setContentPane(patentePanel);
        setSize(550,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        bottoneConferma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    shuttlelive.inserisciPatente(codice.getText(),Date.valueOf(data_conseguimento.getText()),Date.valueOf(data_scadenza.getText()), livello.getText(),autista);
                    new RegistraVeicolo(shuttlelive,autista);
                } catch (Exception ex) {
                    new RegistraPatente(shuttlelive,autista);
                }

                setVisible(false);
            }
        });

}


}
