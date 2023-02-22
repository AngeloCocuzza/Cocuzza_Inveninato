package ui;

import javax.swing.*;
import model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class InserisciRecensione extends javax.swing.JFrame{
    private ShuttleLive shuttlelive;
    private JTextField recensione;
    private JTextField voto;
    private JPanel inserisciRecensione;
    private JTextField inseriscicommento;
    private JTextField inseriscivoto;
    private JButton inviaRecensioneButton;
    private JButton tornaIndietroButton;
    private Utente utente;
    private CorsaViaggio viaggio;
    public InserisciRecensione(ShuttleLive sl,CorsaViaggio viag,Utente utent) {
        this.utente=utent;
        this.viaggio=viag;
        this.shuttlelive = sl;


        setTitle("InserisciRecensione");
        setContentPane(inserisciRecensione);
        setSize(550,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);


        inviaRecensioneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    shuttlelive.inserisciRecensione(viaggio,Integer.valueOf(inseriscivoto.getText()), inseriscicommento.getText());
                    new MenuUtente(shuttlelive,utente);
                } catch (Exception ex) {
                    new InserisciRecensione(shuttlelive,viaggio, utente);
                }
                setVisible(false);
            }
        });

        tornaIndietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new GestisciPrenotazioni(shuttlelive,utente);
                setVisible(false);

            }
        });
    }
}
