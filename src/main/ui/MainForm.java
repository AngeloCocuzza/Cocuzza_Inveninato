package ui;

import model.ShuttleLive;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm extends javax.swing.JFrame {

    private ShuttleLive shuttlelive;

    public MainForm() {
        shuttlelive = ShuttleLive.getInstance();

        setTitle("Main");
        setContentPane(mainpanel);
        setSize(550,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        registratiAutista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegistratiAutistaForm(shuttlelive);
                setVisible(false);
            }
        });
        registratiUtente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        loginUtente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        loginAutista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
    private JButton registratiUtente;
    private JPanel mainpanel;
    private JButton loginUtente;
    private JButton registratiAutista;
    private JButton loginAutista;

    public static void main(String[] args) {
        MainForm mainForm = new MainForm();
    }

}
