package ui;

import model.Corsa;
import model.ShuttleLive;
import model.Veicolo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MostraCorsaFinale extends javax.swing.JFrame {
    private ShuttleLive shuttlelive;

    private Corsa corsa;
    private JPanel elencoautistiPanel;

    public MostraCorsaFinale(ShuttleLive sl, Corsa cor) {//String partenza, String arrivo, Date data_partenza, LocalTime ora_partenza) {
        this.shuttlelive=sl;

        this.corsa = cor;

        //JTable tbautisti = new JTable(0,5);
        //DefaultTableModel modelautisti = (DefaultTableModel) tbautisti.getModel();
        JPanel panel = new JPanel(new GridLayout(0,1));
        JPanel cont = new JPanel();
        JPanel p = new JPanel();
        JPanel scegli = new JPanel();
        JLabel s = new JLabel("Ecco i dati della tua corsa");
        JLabel l = new JLabel( corsa.toString());
                //"partenza = " + corsa.getCitta_partenza() + " arrivo = " + corsa.getCitta_destinazione() + " data partenza = " + corsa.getData_partenza() + "Indirizzo di partenza : " + corsa.getInidirizzo_partenza() + "Indirizzo di destinazione : " + corsa.getIndirizzo_destinazione() +" ora partenza = " + corsa.getOra_partenza()+"autista : " + corsa.getAutista() + "veicolo : " + corsa.getVeicolo().getTarga() +);
        //JButton j = new JButton("Scegli");

        //bottoni.setSize(10,5);

        p.add(l);
        scegli.add(s);

        cont.add(p);
        cont.add(scegli);
        cont.add(panel);


            JButton j = new JButton(" conferma il pagamento  " );
            JPanel bottoni = new JPanel();
            bottoni.add(j);
            panel.add(bottoni);
            j.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    shuttlelive.registraCorsa(corsa);
                    new MenuUtente(shuttlelive,corsa.getUtente());
                }
            });

        }


        //panel.add(new JScrollPane(tbautisti));

        JFrame f = new JFrame();
        //JLabel l = new JLabel();
        //l.setSize(150,150);
        //f.setLayout(new BorderLayout());
        f.setTitle("Scegli Veicolo");
        //f.setContentPane(elencoautistiPanel);
        //f.getContentPane().add(new JScrollPane(tbautisti));
        f.add(cont);

        f.setSize(550,400);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

