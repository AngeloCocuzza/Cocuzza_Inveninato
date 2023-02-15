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
         corsa=new Corsa();

        setTitle("CercaCorsa");
        setContentPane(cercaCorsaPanel);
        setSize(550,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        trovaCorsaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                autistiDisponibili = shuttlelive.cercaAutistiDisponibili(partenza.getText(),arrivo.getText(), Date.valueOf(dataPartenza.getText()), LocalTime.parse(oraPartenza.getText()));
                corsa.setUtente(shuttlelive.getUtenteCorrente());
                System.out.println(autistiDisponibili);
                int km=(int)Math.floor(Math.random()*(100)+1);
                corsa.setAddress(new Address(partenza.getText(),arrivo.getText(),indirizzopart.getText(),indirizzodest.getText(),km));
                //corsa.setCitta_partenza(partenza.getText());
                //corsa.setCitta_destinazione(arrivo.getText());
                corsa.setData_partenza(Date.valueOf(dataPartenza.getText()));
                //corsa.setInidirizzo_partenza(indirizzopart.getText());
                //corsa.setIndirizzo_destinazione(indirizzodest.getText());
                corsa.setOra_partenza(LocalTime.parse(oraPartenza.getText()));
                //Random rand=new Random();

                //corsa.setKm_corsa(km);
                new AutistiDispCorsaCorrente(shuttlelive,autistiDisponibili,corsa);//partenza.getText(),arrivo.getText(),Date.valueOf(dataPartenza.getText()),LocalTime.parse(oraPartenza.getText()));
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
