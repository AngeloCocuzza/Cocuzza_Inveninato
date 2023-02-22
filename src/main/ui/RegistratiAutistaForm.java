package ui;

import model.Autista;
import model.ShuttleLive;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class RegistratiAutistaForm extends javax.swing.JFrame {

    private ShuttleLive shuttlelive;

    private Autista autista;


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
               try {
                   Autista autista;
                   autista = shuttlelive.inserisciNuovoAutista(username.getText(),email.getText(),String.copyValueOf(password.getPassword()),nome.getText(),cognome.getText(),telefono.getText(), Date.valueOf(data.getText()));
                   new RegistraPatente(shuttlelive,autista);
               } catch (Exception ex) {
                   System.out.println(ex.getMessage());
                   new RegistratiAutistaForm(shuttlelive);

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
