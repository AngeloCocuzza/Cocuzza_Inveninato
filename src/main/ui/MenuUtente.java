package ui;
import model.ShuttleLive;
import model.Utente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MenuUtente extends javax.swing.JFrame{
    private ShuttleLive shuttlelive;

    private Utente utente;
    private JButton cercaCorsaButton;
    private JPanel menuUtentePanel;
    private JButton cercaViaggioProgrammatoButton;
    private JButton gestisciPrenotazioniButton;
    private JButton visualizzaRecensioniButton;
    private JButton tornaAlLoginButton;

    public MenuUtente(ShuttleLive sl,Utente user) {

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
                new GestisciPrenotazioni(shuttlelive, utente);
                setVisible(false);
            }
        });
        visualizzaRecensioniButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CercaRecensioniAutista(shuttlelive,utente);
                setVisible(false);
            }
        });
        tornaAlLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainForm();
                setVisible(false);
            }
        });

    }}
