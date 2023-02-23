package ui;

import model.Autista;
import model.ShuttleLive;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuAutista extends javax.swing.JFrame {
    private final Autista autista;
    private ShuttleLive shuttlelive;
    private JButton renditiDisponibileButton;
    private JButton aggiungiVeicoloButton;
    private JButton inserisciViaggioProgrammatoButton;
    private JPanel menuautistaPanel;
    private JButton visualizzaStoricoViaggiButton;
    private JButton tornaAlLoginButton;

    public MenuAutista(ShuttleLive sl, Autista auti){
    this.shuttlelive=sl;
    this.autista=auti;
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
        visualizzaStoricoViaggiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VisualizzaCorseViaggiAutista(shuttlelive, autista);
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
    }
}
