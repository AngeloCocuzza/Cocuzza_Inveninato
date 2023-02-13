package ui;

import model.ShuttleLive;
import model.Utente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginUtenteForm extends javax.swing.JFrame {

    private ShuttleLive shuttlelive;

    private Utente utente;


    public LoginUtenteForm(ShuttleLive sl) {
        this.shuttlelive = sl;

        setTitle("LogUtente");
        setContentPane(loginUPanel);
        setSize(550, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        accediButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    utente = shuttlelive.loginUtente(email.getText(), String.copyValueOf(password.getPassword()));
                    new MenuUtente(shuttlelive,utente);
                } catch (Exception ex) {
                    new LoginUtenteForm(shuttlelive);
                }
                setVisible(false);
                //System.out.println(utente);
            }
        });


    }

    private JButton accediButton;
    private JTextField email;
    private JLabel passwordLabel;
    private JLabel Email;
    private JPanel loginUPanel;
    private JPasswordField password;
}