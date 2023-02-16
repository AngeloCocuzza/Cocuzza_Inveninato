package ui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ScegliViaggio {


    private CorseController controller;
    private List<ViaggioProgrammato> viaggi;
    private Corsa corsa;

    public ScegliViaggio(CorseController ctrl, Utente user, List<ViaggioProgrammato> viag) {
        this.controller = ctrl;
        this.viaggi = viag;

        JPanel panel = new JPanel(new GridLayout(0,1));
        JPanel cont = new JPanel();
        JPanel p = new JPanel();
        JPanel scegli = new JPanel();
        JPanel torna = new JPanel();
        JButton t = new JButton("Scegli");
        torna.add(t);
        JLabel s = new JLabel("Scegli veicolo");

        scegli.add(s);

        cont.add(p);
        cont.add(scegli);
        cont.add(panel);
        cont.add(torna);

        JFrame f = new JFrame();
        f.setTitle("Scegli Viaggio");
        f.add(cont);

        f.setSize(550,400);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setVisible(true);
/*
        for(ViaggioProgrammato viaggio : viaggi) {
            JPanel v1 = new JPanel();
            JPanel v2 = new JPanel();
            //JLabel ad = new JLabel("Citta Partenza: " + );
            JLabel info = new JLabel();
            //JButton j = new JButton(" ID : "+viaggio.getID() + " Autista :  " + viaggio.getAutista().getUsername() + " Veicolo :  " + viaggio.getVeicolo().getTarga()+ " Citta di partenza : "+ viaggio.ge.getN_posti() + " Colore :   "+ veicolo.getColore()+ "  " );
            JPanel bottoni = new JPanel();
            bottoni.add(j);
            panel.add(v1);
            panel.add(v2);
            panel.add(bottoni);
            j.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                  //  corsa.setVeicolo(veicolo);
                    corsa.setPrezzo(corsa.getFee());
                    //new MostraCorsaFinale(shuttlelive,corsa);
                    f.setVisible(false);
                }
            });

        }*/
    }

}