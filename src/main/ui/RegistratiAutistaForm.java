package ui;

import model.ShuttleLive;

import javax.swing.*;

public class RegistratiAutistaForm extends javax.swing.JFrame {

    private ShuttleLive shuttlelive;


    public RegistratiAutistaForm(ShuttleLive sl) {
        this.shuttlelive=sl;

        setTitle("RegAutista");
        setContentPane(registrazionePanel);
        setSize(550,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JPanel registrazionePanel;
    private JTextField textField1;
    private JTextField textField2;
    private JPasswordField passwordField1;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
}
