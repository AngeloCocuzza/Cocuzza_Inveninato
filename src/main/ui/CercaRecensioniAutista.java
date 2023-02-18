
package ui;

import model.ShuttleLive;
import model.Utente;

import javax.swing.*;
import java.util.List;
import model.*;

public class CercaRecensioniAutista extends javax.swing.JFrame {
    private ShuttleLive shuttlelive;

    private Utente utente;
    private List<Recensione> recensioniAutisti;
    private CorseController controller;
    private JButton cercaRecensioni;
    private JTextField autista;
    private JPanel cerca;

    public CercaRecensioniAutista(ShuttleLive sl, Utente user, CorseController cl) {
        this.controller=cl;

        this.shuttlelive=sl;
        this.utente=user;


        setTitle("CercaCorsa");
        setContentPane(cercaRecensioni);
        setSize(550,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
