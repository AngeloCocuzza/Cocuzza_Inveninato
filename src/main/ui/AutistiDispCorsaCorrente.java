package ui;

import model.Autista;
import model.ShuttleLive;
import model.Utente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        //JTable tbautisti = new JTable(0,5);
        //DefaultTableModel modelautisti = (DefaultTableModel) tbautisti.getModel();
        JPanel panel = new JPanel(new GridLayout(0,1));
        JPanel cont = new JPanel();
        JPanel p = new JPanel();
        JPanel scegli = new JPanel();
        JLabel s = new JLabel("Scegli autista");
        JLabel l = new JLabel("partenza = " + partenza + " arrivo = " + arrivo + " data partenza = " + data_partenza + " ora partenza = " + ora_partenza);
        //JButton j = new JButton("Scegli");

        //bottoni.setSize(10,5);

        p.add(l);
        scegli.add(s);

        cont.add(p);
        cont.add(scegli);
        cont.add(panel);

        for(Autista autista : autistiDisponibili) {
            //modelautisti.addRow(autista.toArray());
            JButton j = new JButton(autista.getUsername() + "  " + autista.getTelefono() + "  " + autista.getEmail());
            JPanel bottoni = new JPanel();
            bottoni.add(j);
            panel.add(bottoni);
            j.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new ScegliVeicolo(shuttlelive,autista,)
                }
            });

        }


        //panel.add(new JScrollPane(tbautisti));

        JFrame f = new JFrame();
        //JLabel l = new JLabel();
        //l.setSize(150,150);
        //f.setLayout(new BorderLayout());
        f.setTitle("Scegli Autista");
        //f.setContentPane(elencoautistiPanel);
        //f.getContentPane().add(new JScrollPane(tbautisti));
        f.add(cont);

        f.setSize(550,400);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
