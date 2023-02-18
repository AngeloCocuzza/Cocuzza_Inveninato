package ui;
import model.CorseController;
import model.ShuttleLive;
import model.Utente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MenuUtente extends javax.swing.JFrame{
    private ShuttleLive shuttlelive;

    private CorseController controller;

    private Utente utente;
    private JButton cercaCorsaButton;
    private JPanel menuUtentePanel;
    private JButton cercaViaggioProgrammatoButton;
    private JButton gestisciPrenotazioniButton;
    private JButton visualizzaRecensioniButton;

    public MenuUtente(ShuttleLive sl,Utente user) {
        controller = CorseController.getInstance();

    this.shuttlelive = sl;
    this.utente=user;

    setTitle("MenuUtente");
    setContentPane(menuUtentePanel);
    setSize(550, 400);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setVisible(true);
        cercaCorsaButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new CercaCorsa(shuttlelive,utente);
            setVisible(false);
            //System.out.println(utente);
        }
    });
        cercaViaggioProgrammatoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CercaViaggioProgrammato(shuttlelive,utente);
                setVisible(false);
            }
        });
        gestisciPrenotazioniButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GestisciPrenotazioni(shuttlelive,controller,utente);
                setVisible(false);
            }
        });
    }}
