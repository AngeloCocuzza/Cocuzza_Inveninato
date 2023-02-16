package ui;

import model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalTime;

public class InserisciViaggio extends javax.swing.JFrame {
    private ShuttleLive shuttlelive;
    private CorseController controller;

    private Autista autista;
    private ViaggioProgrammato viaggio;
    private JTextField dataViaggio;
    private JTextField oraPartenza;
    private JTextField cittaPartenza;
    private JTextField luogoEvento;
    private JTextField evento;
    private JTextField veicolo;
    private JTextField prezzo;
    private JTextField indPartenza;
    private JTextField indDest;
    private JPanel inserisciViaggio;
    private JButton creaViaggio;
    private JButton tornaAlMenuPrincipaleButton;
    private JTextField km;

    public InserisciViaggio(ShuttleLive sl,Utente user) {
        this.shuttlelive=sl;
        this.autista=autista;
        controller=CorseController.getInstance();

        setTitle("InserisciViaggio");
        setContentPane(inserisciViaggio);
        setSize(550,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        creaViaggio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    viaggio.setEvento(evento.getText());
                    viaggio.setPrezzo(Float.parseFloat(prezzo.getText()));
                    viaggio.setAutista(autista);
                    viaggio.setAddress(new Address(cittaPartenza.getText(),luogoEvento.getText(),indPartenza.getText(),indDest.getText(), Integer.parseInt(km.getText())));
                    viaggio.setVeicolo(controller.veicoloSingoloByName(veicolo.getText()));
                    viaggio.setPostiDisponibili();
                    viaggio.setData_partenza(Date.valueOf(dataViaggio.getText()));
                    viaggio.setOra_partenza(LocalTime.parse(oraPartenza.getText()));
                    System.out.println(viaggio);
                    viaggio=shuttlelive.inserisciViaggio(viaggio);
                    System.out.println(viaggio);

                    new MenuAutista(shuttlelive,autista);
                } catch (Exception ex) {
                    new InserisciViaggio(shuttlelive,autista);
                }



                setVisible(false);
            }
        });

        tornaAlMenuPrincipaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new MenuUtente(shuttlelive,autista);
                setVisible(false);

            }
        });
    }}