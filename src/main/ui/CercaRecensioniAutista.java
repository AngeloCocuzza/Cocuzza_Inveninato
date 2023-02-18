
package ui;

import model.ShuttleLive;
import model.Utente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;
import model.*;

public class CercaRecensioniAutista extends javax.swing.JFrame {
    private ShuttleLive shuttlelive;

    private Utente utente;
    private List<Recensione> recensioniAutista;
    private CorseController controller;
    private JButton cercaRecensioni;
    private JTextField autista;
    private JPanel cerca;

    public CercaRecensioniAutista(ShuttleLive sl, Utente user, CorseController cl) {
        this.controller=cl;

        this.shuttlelive=sl;
        this.utente=user;

List<Recensione> recensioni;
        setTitle("CercaCorsa");
        setContentPane(cercaRecensioni);
        setSize(550,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        cercaRecensioni.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String autist=autista.getText();
                     recensioniAutista= controller.selezionaRecensioniAutista(autist);
                    new VisualizzaRecensioniAutista(shuttlelive,controller,utente,recensioniAutista,autist);
                } catch (Exception ex) {
                    new CercaRecensioniAutista(shuttlelive,utente,controller);
                }
                setVisible(false);

            }
        });
    }}
