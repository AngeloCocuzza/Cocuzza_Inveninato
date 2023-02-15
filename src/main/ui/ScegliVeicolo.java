package ui;

import model.Autista;
import model.Corsa;
import model.ShuttleLive;
import model.Veicolo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ScegliVeicolo {
    private ShuttleLive shuttlelive;
    private List<Veicolo> veicoliDisp;
    private Corsa corsa;
    private JPanel elencoautistiPanel;

    public ScegliVeicolo(ShuttleLive sl, List<Veicolo> veicoli, Corsa cor) {//String partenza, String arrivo, Date data_partenza, LocalTime ora_partenza) {
        this.shuttlelive=sl;
        this.veicoliDisp = veicoli;
        this.corsa = cor;

        //JTable tbautisti = new JTable(0,5);
        //DefaultTableModel modelautisti = (DefaultTableModel) tbautisti.getModel();
        JPanel panel = new JPanel(new GridLayout(0,1));
        JPanel cont = new JPanel();
        JPanel p = new JPanel();
        JPanel scegli = new JPanel();
        JLabel s = new JLabel("Scegli veicolo");
        JLabel l = new JLabel("partenza = " + corsa.getAddress().getCitta_partenza() + " arrivo = " + corsa.getAddress().getCitta_destinazione() + " data partenza = " + corsa.getData_partenza() + " ora partenza = " + corsa.getOra_partenza());
        //JButton j = new JButton("Scegli");

        //bottoni.setSize(10,5);

        p.add(l);
        scegli.add(s);

        cont.add(p);
        cont.add(scegli);
        cont.add(panel);

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

        for(Veicolo veicolo : veicoliDisp) {
            //modelautisti.addRow(autista.toArray());
            JButton j = new JButton(" Targa : "+veicolo.getTarga() + " Marca :  " + veicolo.getMarca() + " Modello :  " + veicolo.getModello()+ " N. Posti : "+ veicolo.getN_posti() + " Colore :   "+ veicolo.getColore()+ "  " );
            JPanel bottoni = new JPanel();
            bottoni.add(j);
            panel.add(bottoni);
            j.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    corsa.setVeicolo(veicolo);
                    corsa.setPrezzo(corsa.getFee());
                    new MostraCorsaFinale(shuttlelive,corsa);
                    f.setVisible(false);
                }
            });

        }


        //panel.add(new JScrollPane(tbautisti));


    }
}


