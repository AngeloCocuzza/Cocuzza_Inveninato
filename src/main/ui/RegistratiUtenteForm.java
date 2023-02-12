package ui;

import model.ShuttleLive;
import model.Utente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class RegistratiUtenteForm extends javax.swing.JFrame{

    private ShuttleLive shuttlelive;

    private Utente utente;


    public RegistratiUtenteForm(ShuttleLive sl) {
        this.shuttlelive=sl;

        setTitle("RegUtente");
        setContentPane(registrazionePanel);
        setSize(550,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        inviabutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    utente = shuttlelive.inserisciNuovoUtente(username.getText(),email.getText(),String.copyValueOf(password.getPassword()),nome.getText(),cognome.getText(),telefono.getText(), Date.valueOf(data.getText()));
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    new RegistratiUtenteForm(shuttlelive); // forse
                    //throw new RuntimeException(ex); //potrebbe rilanciare il form
                }
                setVisible(false);
            }
        });
    }
    private JPanel registrazionePanel;
    private JTextField username;
    private JTextField email;
    private JPasswordField password;
    private JTextField nome;
    private JTextField cognome;
    private JTextField telefono;
    private JTextField data;
    private JButton inviabutton;
}
