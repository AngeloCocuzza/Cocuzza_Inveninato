package ui;

import model.CorseController;
import model.ShuttleLive;
import model.Utente;
import model.ViaggioProgrammato;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

public class CercaViaggioProgrammato extends javax.swing.JFrame {

    private ShuttleLive shuttlelive;
    private Utente utente;

    private CorseController controller;

    private List<ViaggioProgrammato> viaggi;
    private JPanel viaggioprogrammato;
    private JTextField evento;
    private JTextField dataviaggio;
    private JButton cercaViaggioButton;
    private JLabel eventoLabel;

    public CercaViaggioProgrammato(ShuttleLive sl, Utente user) {

        controller = CorseController.getInstance();
        this.shuttlelive = sl;
        this.utente = user;

        setTitle("Cerca Viaggio");
        setContentPane(viaggioprogrammato);
        setSize(550, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);


        cercaViaggioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    viaggi = controller.selezionaViaggioProgrammato(evento.getText(), Date.valueOf(dataviaggio.getText()));
                    new ScegliViaggio(shuttlelive,controller,utente,viaggi);
                } catch (Exception ex) {
                    new CercaViaggioProgrammato(shuttlelive,utente);
                }
                setVisible(false);

            }
        });
    }
}
