package ui;

import model.*;

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
    private List<Veicolo> veicoli;
    private Corsa corsa;
    private JPanel elencoautistiPanel;

    public AutistiDispCorsaCorrente(ShuttleLive sl, List<Autista> autisti, Corsa cor) {//String partenza, String arrivo, Date data_partenza, LocalTime ora_partenza) {
        this.shuttlelive=sl;
        this.autistiDisponibili = autisti;
        this.corsa = cor;

        JFrame f = new JFrame();
        //JLabel l = new JLabel();
        //l.setSize(150,150);
        //f.setLayout(new BorderLayout());
        f.setTitle("Scegli Autista");
        //f.setContentPane(elencoautistiPanel);
        //f.getContentPane().add(new JScrollPane(tbautisti));


        f.setSize(550,400);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setVisible(true);

        //JTable tbautisti = new JTable(0,5);
        //DefaultTableModel modelautisti = (DefaultTableModel) tbautisti.getModel();
        JPanel panel = new JPanel(new GridLayout(0,1));
        JPanel cont = new JPanel();
        JPanel p = new JPanel();
        JPanel scegli = new JPanel();
        JLabel s = new JLabel("Scegli autista");
        JLabel l = new JLabel("partenza = " + corsa.getCitta_partenza() + " arrivo = " + corsa.getCitta_destinazione() + " data partenza = " + corsa.getData_partenza() + " ora partenza = " + corsa.getOra_partenza());
        //JButton j = new JButton("Scegli");

        //bottoni.setSize(10,5);
        f.add(cont);
        p.add(l);
        scegli.add(s);
        cont.add(scegli);
        cont.add(p);

        cont.add(panel);

        for(Autista autista : autistiDisponibili) {
            //modelautisti.addRow(autista.toArray());
            JButton j = new JButton("username : " + autista.getUsername() + " telefono : " + autista.getTelefono() + " email : " + autista.getEmail());
            JPanel bottoni = new JPanel();
            bottoni.add(j);
            panel.add(bottoni);
            j.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    corsa.setAutista(autista);
                    veicoli= shuttlelive.veicoliAutista(autista.getUsername());
                    new ScegliVeicolo(shuttlelive,veicoli,corsa);
                    f.setVisible(false);
                }
            });

        }


        //panel.add(new JScrollPane(tbautisti));


    }
}
