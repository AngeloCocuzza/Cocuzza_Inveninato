package ui;

import model.Autista;
import model.ShuttleLive;
import model.Utente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class CercaCorsa extends javax.swing.JFrame {
    private ShuttleLive shuttlelive;

    private Utente utente;
    private List<Autista> autistiDisponibili;

    public CercaCorsa(ShuttleLive sl,Utente user) {
        this.shuttlelive=sl;
        this.utente=user;


        setTitle("CercaCorsa");
        setContentPane(cercaCorsaPanel);
        setSize(550,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        trovaCorsaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    autistiDisponibili = shuttlelive.cercaAutistiDisponibili(partenza.getText(),arrivo.getText(), Date.valueOf(dataPartenza.getText()), LocalTime.parse(oraPartenza.getText()));
                    //new AutistiDisp(shuttlelive,autistiDisponibili);
                } catch (Exception ex) {
                    new CercaCorsa(sl,user);
                }

                setVisible(false);
            }
        });
    }
    private JButton trovaCorsaButton;
    private JTextField partenza;
    private JTextField arrivo;
    private JTextField dataPartenza;
    private JTextField oraPartenza;
    private JLabel si;
    private JLabel giornoPartenza;
    private JLabel ora;
    private JPanel cercaCorsaPanel;
}
