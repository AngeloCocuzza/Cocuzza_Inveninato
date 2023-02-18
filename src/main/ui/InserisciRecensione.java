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
    private CorseController controller;

    private Utente utente;
    private CorsaViaggio viaggio;
    public InserisciRecensione(ShuttleLive sl,CorseController cl,CorsaViaggio viag,Utente utent) {
        this.controller=cl;
        this.utente=utent;
        this.viaggio=viag;
        this.shuttlelive = sl;


        setTitle("InserisciViaggio");
        setContentPane(inserisciRecensione);
        setSize(550,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);


        inviaRecensioneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Recensione review=new Recensione(Integer.parseInt(voto.getText()), recensione.getText());
                    viaggio.getRecensione();
                    viaggio.setRecensione(review);
                    System.out.println(viaggio.getRecensione());
                    controller.inserisciRecensione(viaggio);


                } catch (Exception ex) {
                    new InserisciRecensione(shuttlelive,controller,viaggio, utente);
                }



                setVisible(false);
            }
        });

        tornaIndietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new GestisciPrenotazioni(shuttlelive,controller,utente);
                setVisible(false);

            }
        });
    }
}
