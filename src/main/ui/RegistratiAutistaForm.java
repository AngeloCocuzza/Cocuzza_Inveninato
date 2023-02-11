package ui;

import model.ShuttleLive;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class RegistratiAutistaForm extends javax.swing.JFrame {

    private ShuttleLive shuttlelive;


    public RegistratiAutistaForm(ShuttleLive sl) {
        this.shuttlelive=sl;

        setTitle("RegAutista");
        setContentPane(registrazionePanel);
        setSize(550,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        inviabutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shuttlelive.inserisciNuovoAutista(username.getText(),email.getText(),String.copyValueOf(password.getPassword()),nome.getText(),cognome.getText(),telefono.getText(), Date.valueOf(data.getText()));
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
