package ui;

import model.Autista;
import model.ShuttleLive;
import model.Utente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class AutistiDispCorsaCorrente extends javax.swing.JFrame {

    private ShuttleLive shuttlelive;
    private List<Autista> autistiDisponibili;
    private JPanel elencoautistiPanel;

    public AutistiDispCorsaCorrente(ShuttleLive sl, List<Autista> autisti, String partenza, String arrivo, Date data_partenza, LocalTime ora_partenza) {
        this.shuttlelive=sl;
        this.autistiDisponibili = autisti;

        JTable tbautisti = new JTable(0,5);
        DefaultTableModel modelautisti = (DefaultTableModel) tbautisti.getModel();

        for(Autista autista : autistiDisponibili) {
            modelautisti.addRow(autista.toArray());
        }

        JFrame f = new JFrame();
        //JLabel l = new JLabel();
        //l.setSize(150,150);
        f.setTitle("partenza = " + partenza + " arrivo = " + arrivo + " data partenza = " + data_partenza + " ora partenza = " + ora_partenza);
        //f.setContentPane(elencoautistiPanel);
        f.getContentPane().add(new JScrollPane(tbautisti));

        f.setSize(550,400);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
