package ui;

import model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

public class CercaCorsa extends javax.swing.JFrame {
    private ShuttleLive shuttlelive;

    private Utente utente;
    private List<Autista> autistiDisponibili;
    private Corsa corsa;

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
                    autistiDisponibili = shuttlelive.cercaAutistiDisponibili(partenza.getText(), arrivo.getText(), Date.valueOf(dataPartenza.getText()), LocalTime.parse(oraPartenza.getText()));
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    System.out.println(autistiDisponibili);


                    new AutistiDispCorsaCorrente(shuttlelive, autistiDisponibili,utente,partenza.getText(),arrivo.getText(),Date.valueOf(dataPartenza.getText()),LocalTime.parse(oraPartenza.getText()),indirizzopart.getText(), indirizzodest.getText());
                    setVisible(false);
            }
        });

        tornaAlMenuPricipaleButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

                new MenuUtente(shuttlelive,utente);
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
    private JButton tornaAlMenuPricipaleButton;
    private JTextField indirizzopart;
    private JTextField indirizzodest;
}
