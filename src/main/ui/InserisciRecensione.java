package ui;

import javax.swing.*;
import model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class InserisciRecensione extends javax.swing.JFrame{
    private JTextField recensione;
    private JTextField voto;
    private JPanel inserisciRecensione;
    private JButton inviaRecensioneButton;
    private CorseController controller;

    private Utente utente;
    private CorsaViaggio viaggio;
    public InserisciRecensione(CorseController cl,CorsaViaggio viag,Utente utent) {
        this.controller=cl;
        this.utente=utent;
        this.viaggio=viag;


        setTitle("InserisciViaggio");
        setContentPane(inserisciRecensione);
        setSize(550,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);


        inviaRecensioneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Recensione review=new Recensione(voto.getText(), recensione.getText());
                viaggio.setRecensione(review);
                   controller.inserisciReview(viaggio);


                } catch (Exception ex) {
                    new InserisciRecensione(autista);
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
    }
