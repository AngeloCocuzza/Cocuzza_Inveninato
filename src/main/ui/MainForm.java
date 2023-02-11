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
        setContentPane(panel1);
        setSize(550,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
    private JButton registratiButton;
    private JPanel panel1;
    private JButton loginButton;
    private JButton registratiButton1;
    private JButton loginButton1;

    public static void main(String[] args) {
        MainForm mainForm = new MainForm();
    }

}
