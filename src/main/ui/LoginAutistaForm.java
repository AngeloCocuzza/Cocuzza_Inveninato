package ui;

import model.Autista;
import model.ShuttleLive;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginAutistaForm extends javax.swing.JFrame{

    private ShuttleLive shuttlelive;

    private Autista autista;

    public LoginAutistaForm(ShuttleLive sl) {
        this.shuttlelive=sl;

        setTitle("LogAutista");
        setContentPane(loginPanel);
        setSize(550,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        accediButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                autista = shuttlelive.loginAutista(email.getText(),String.copyValueOf(password.getPassword()));
                setVisible(false);
            }
        });
    }

    private JButton accediButton;
    private JTextField email;
    private JPasswordField password;
    private JPanel loginPanel;
    private JLabel emailLabel;
}
