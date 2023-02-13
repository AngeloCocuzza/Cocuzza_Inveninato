package ui;

import model.Autista;
import model.ShuttleLive;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AggiungiDisponibilita extends javax.swing.JFrame {

    private ShuttleLive shuttlelive;
    private JTextField datadispo;
    private JTextField orainizio;
    private JTextField orafine;
    private JButton inserisciDisponibilitàButton;
    private JPanel disponibilitaPanel;

    public AggiungiDisponibilita(ShuttleLive sl, Autista autista) {
    this.shuttlelive=sl;
    setTitle("Aggiungi disponibilità");
    setContentPane(disponibilitaPanel);
    setSize(550,400);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setVisible(true);
    inserisciDisponibilitàButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    });
}
}
