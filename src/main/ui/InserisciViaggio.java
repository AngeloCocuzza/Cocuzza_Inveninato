package ui;

import model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalTime;

public class InserisciViaggio extends javax.swing.JFrame {
    private ShuttleLive shuttlelive;

    private Autista autista;
    private Corsa corsa;
    private JTextField dataViaggio;
    private JTextField oraPartenza;
    private JTextField cittaPartenza;
    private JTextField textField4;
    private JTextField evento;
    private JTextField veicolo;
    private JTextField prezzo;
    private JTextField indPartenza;
    private JTextField indDest;
    private JPanel inserisciViaggio;

    public InserisciViaggio(ShuttleLive sl,Utente user) {
        this.shuttlelive=sl;
        this.autista=autista;
        corsa=new Corsa();

        setTitle("InserisciViaggio");
        setContentPane(inserisciViaggioPanel);
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
                corsa.setUtente(shuttlelive.getUtenteCorrente());
                System.out.println(autistiDisponibili);
                int km = (int) Math.floor(Math.random() * (100) + 1);
                corsa.setAddress(new Address(partenza.getText(), arrivo.getText(), indirizzopart.getText(), indirizzodest.getText(), km));
                corsa.setData_partenza(Date.valueOf(dataPartenza.getText()));
                corsa.setOra_partenza(LocalTime.parse(oraPartenza.getText()));

                new AutistiDispCorsaCorrente(shuttlelive, autistiDisponibili, corsa);//partenza.getText(),arrivo.getText(),Date.valueOf(dataPartenza.getText()),LocalTime.parse(oraPartenza.getText()));
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