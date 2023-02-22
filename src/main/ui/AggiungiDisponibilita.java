package ui;

import model.Autista;
import model.Disponibilita;
import model.ShuttleLive;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalTime;

public class AggiungiDisponibilita extends javax.swing.JFrame {

    private ShuttleLive shuttlelive;
    private Autista autista;
    private JTextField datadispo;
    private JTextField orainizio;
    private JTextField orafine;
    private JButton inserisciDisponibilitàButton;
    private JPanel disponibilitaPanel;
    private JTextField cittapart;
    private JButton tornaAlMenuPrincipaleButton;

    public AggiungiDisponibilita(ShuttleLive sl, Autista autist) {
        this.autista=autist;
    this.shuttlelive=sl;
    setTitle("Aggiungi disponibilità");
    setContentPane(disponibilitaPanel);
    setSize(550,400);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setVisible(true);
    inserisciDisponibilitàButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                shuttlelive.inserisciNuovaDisponibilita(autista, Date.valueOf(datadispo.getText()),LocalTime.parse(orainizio.getText()), LocalTime.parse(orafine.getText()),cittapart.getText());
                new MenuAutista(shuttlelive,autista);
            } catch (Exception ex) {
                new AggiungiDisponibilita(shuttlelive,autista);
            }
            setVisible(false);
        }
    });
        tornaAlMenuPrincipaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new MenuAutista(shuttlelive,autista);
                setVisible(false);

            }
        });
}
}
