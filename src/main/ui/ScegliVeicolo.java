package ui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ScegliVeicolo {
    private ShuttleLive shuttlelive;
    private Map<String,Veicolo> veicoliDisp;
    private Corsa corsa;
    private JPanel elencoautistiPanel;

    private CorseController controller;

    public ScegliVeicolo(ShuttleLive sl, Map<String,Veicolo> veicoli,Autista autista, Utente utente, String c_partenza, String c_arrivo, Date data_partenza, LocalTime ora_partenza, String indirizzopart, String indirizzodest) {//String partenza, String arrivo, Date data_partenza, LocalTime ora_partenza) {
        this.shuttlelive=sl;
        this.veicoliDisp = veicoli;
        controller = CorseController.getInstance();


        //JTable tbautisti = new JTable(0,5);
        //DefaultTableModel modelautisti = (DefaultTableModel) tbautisti.getModel();
        JPanel panel = new JPanel(new GridLayout(0,1));
        JPanel cont = new JPanel();
        JPanel p = new JPanel();
        JPanel scegli = new JPanel();
        JPanel torna = new JPanel();
        JButton t = new JButton("Torna indietro");
        torna.add(t);

        JLabel s = new JLabel("Scegli veicolo");
        JLabel l = new JLabel("partenza = " + c_partenza + " arrivo = " + c_arrivo + " data partenza = " + data_partenza + " ora partenza = " + ora_partenza);
        //JButton j = new JButton("Scegli");

        //bottoni.setSize(10,5);

        p.add(l);
        scegli.add(s);

        cont.add(p);
        cont.add(scegli);
        cont.add(panel);
        cont.add(torna);

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

        for(Veicolo veicolo : veicoli.values()) {
            //modelautisti.addRow(autista.toArray());
            JButton j = new JButton(" Targa : "+veicolo.getTarga() + " Marca :  " + veicolo.getMarca() + " Modello :  " + veicolo.getModello()+ " N. Posti : "+ veicolo.getN_posti() + " Colore :   "+ veicolo.getColore()+ "  " );
            JPanel bottoni = new JPanel();
            bottoni.add(j);
            panel.add(bottoni);
            j.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Corsa corsa;
                    corsa = controller.creaCorsa(veicolo,autista,utente,c_partenza,c_arrivo,data_partenza,ora_partenza,indirizzopart,indirizzodest);
                    new MostraCorsaFinale(shuttlelive,corsa,controller);
                    f.setVisible(false);
                }
            });

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


