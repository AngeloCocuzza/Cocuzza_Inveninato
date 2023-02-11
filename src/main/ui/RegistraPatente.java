package ui;

import model.Autista;
import model.ShuttleLive;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class RegistraPatente extends javax.swing.JFrame {
    private ShuttleLive shuttlelive;
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


    public RegistraPatente(ShuttleLive sl,Autista autista) {
        this.shuttlelive=sl;
        setTitle("RegPatente");
        setContentPane(patentePanel);
        setSize(550,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        bottoneConferma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shuttlelive.inserisciPatente(cod.getText(),autista,Date.valueOf(data_conseguimento.getText()),Date.valueOf(data_scadenza.getText()), liv.getText());
                new RegistraVeicolo(shuttlelive,autista);
                setVisible(false);
            }
        });

}


}
