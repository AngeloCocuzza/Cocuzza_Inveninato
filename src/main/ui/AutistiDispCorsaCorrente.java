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
import java.util.Map;

public class AutistiDispCorsaCorrente extends javax.swing.JFrame {

    private ShuttleLive shuttlelive;
    private List<Autista> autistiDisponibili;
    private Map<String,Veicolo> veicoli;
    private Corsa corsa;
    private JPanel elencoautistiPanel;

    public AutistiDispCorsaCorrente(ShuttleLive sl, List<Autista> autisti, Utente utente, String c_partenza,String c_arrivo,Date data_partenza,LocalTime ora_partenza,String indirizzopart, String indirizzodest ) {
        this.shuttlelive=sl;
        this.autistiDisponibili = autisti;

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
        JPanel torna = new JPanel();
        JLabel s = new JLabel("Scegli autista");
        JLabel l = new JLabel("partenza = " + c_partenza + " arrivo = " + c_arrivo + " data partenza = " + data_partenza + " ora partenza = " + ora_partenza);
        JButton t = new JButton("Torna Indietro");

        //bottoni.setSize(10,5);
        f.add(cont);
        p.add(l);
        scegli.add(s);
        cont.add(scegli);
        cont.add(p);
        torna.add(t);

        cont.add(panel);
        cont.add(torna);

        if(autistiDisponibili.isEmpty()) {
            JLabel vuoto = new JLabel("Non ci sono autisti disponibili per quel giorno");
            panel.add(vuoto);
        } else {
            for (Autista autista : autistiDisponibili) {
                //modelautisti.addRow(autista.toArray());
                JButton j = new JButton("username : " + autista.getUsername() + " telefono : " + autista.getTelefono() + " email : " + autista.getEmail());
                JPanel bottoni = new JPanel();
                bottoni.add(j);
                panel.add(bottoni);
                j.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        veicoli = shuttlelive.veicoliAutista(autista.getUsername());
                        new ScegliVeicolo(shuttlelive, veicoli, autista,utente, c_partenza, c_arrivo, data_partenza, ora_partenza, indirizzopart, indirizzodest);
                        f.setVisible(false);
                    }
                });

            }
        }

        t.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CercaCorsa(shuttlelive,corsa.getUtente());
                f.setVisible(false);
            }
        });


        //panel.add(new JScrollPane(tbautisti));


    }
}
