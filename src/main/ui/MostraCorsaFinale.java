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
        JPanel r = new JPanel();
        JPanel torna = new JPanel();
        JPanel scegli = new JPanel();
        JLabel s = new JLabel("Ecco i dati della tua corsa");
        JLabel l = new JLabel( "Autista : " + corsa.getAutista().getUsername() + " Utente : " + corsa.getUtente().getUsername() + " Veicolo : " + corsa.getVeicolo().getTarga() +   " Prezzo : " + corsa.getFee() + "");
        JLabel d = new JLabel(" Data partenza = " + corsa.getData_partenza() + " Tratta : " + corsa.getAddress() + " Ora partenza : " + corsa.getOra_partenza());
        JButton t = new JButton("Torna Indietro");
      torna.add(t);
        //bottoni.setSize(10,5);
        JFrame f = new JFrame();
        //JLabel l = new JLabel();
        //l.setSize(150,150);
        //f.setLayout(new BorderLayout());
        f.setTitle("registraCorsa");
        //f.setContentPane(elencoautistiPanel);
        //f.getContentPane().add(new JScrollPane(tbautisti));
        f.add(cont);

        f.setSize(550,400);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setVisible(true);

        p.add(l);
        r.add(d);
        scegli.add(s);


        cont.add(scegli);
        cont.add(p);
        cont.add(r);
        cont.add(panel);
        cont.add(torna);



            JButton j = new JButton(" conferma la prenotazione  " );
            JPanel bottoni = new JPanel();
            bottoni.add(j);
            panel.add(bottoni);
            j.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        shuttlelive.inserisciCorsa(corsa);
                        new MenuUtente(shuttlelive,corsa.getUtente());
                    } catch (Exception ex) {
                        new CercaCorsa(shuttlelive,corsa.getUtente());
                    }

                    f.setVisible(false);
                }
            });
        t.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CercaCorsa(shuttlelive,corsa.getUtente());
                f.setVisible(false);
            }
        });




        //panel.add(new JScrollPane(tbautisti));


    }}

