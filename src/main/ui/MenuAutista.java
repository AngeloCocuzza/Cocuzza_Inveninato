package ui;

import model.Autista;
import model.ShuttleLive;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuAutista extends javax.swing.JFrame {
    private ShuttleLive shuttlelive;
    private JButton renditiDisponibileButton;
    private JButton aggiungiVeicoloButton;
    private JButton inserisciViaggioProgrammatoButton;
    private JPanel menuautistaPanel;
    private JButton button1;

    public MenuAutista(ShuttleLive sl, Autista autista) {
    this.shuttlelive=sl;
    setTitle("Menù autista");
    setContentPane(menuautistaPanel);
    setSize(550,400);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setVisible(true);
    renditiDisponibileButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new AggiungiDisponibilita(shuttlelive,autista);
            setVisible(false);
        }
    });
        aggiungiVeicoloButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegistraVeicolo(shuttlelive,autista);
                setVisible(false);

            }
        });
        inserisciViaggioProgrammatoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InserisciViaggio(shuttlelive,autista);
                setVisible(false);
                //System.out.println(utente);
            }
        });
    }
}
