
package ui;

import model.ShuttleLive;
import model.Utente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import model.*;

public class CercaRecensioniAutista extends javax.swing.JFrame {
    private ShuttleLive shuttlelive;

    private Utente utente;
    private List<Recensione> recensioniAutista;
    private JButton cercaRecensioni;
    private JTextField autista;
    private JPanel cerca;
    private JButton cercaRecensioniButton;

    public CercaRecensioniAutista(ShuttleLive sl, Utente user) {

        this.shuttlelive=sl;
        this.utente=user;

List<Recensione> recensioni;
        setTitle("CercaRecensioni");
        setContentPane(cerca);
        setSize(550,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        cercaRecensioniButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String autist=autista.getText();
                     recensioniAutista= shuttlelive.selezionaRecensioniAutista(autist);
                    new VisualizzaRecensioniAutista(shuttlelive,utente,recensioniAutista,autist);
                } catch (Exception ex) {
                    new CercaRecensioniAutista(shuttlelive,utente);
                }
                setVisible(false);

            }
        });
    }}
