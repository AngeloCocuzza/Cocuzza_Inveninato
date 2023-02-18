package ui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ScegliViaggio {


    private CorseController controller;

    private ShuttleLive shuttlelive;
    private List<ViaggioProgrammato> viaggi;

    private Utente utente;

    public ScegliViaggio(ShuttleLive sl,CorseController ctrl, Utente user, List<ViaggioProgrammato> viag) {
        this.controller = ctrl;
        this.shuttlelive = sl;
        this.viaggi = viag;
        this.utente = user;

        JPanel panel = new JPanel(new GridLayout(0,1));
        JPanel cont = new JPanel();
        JPanel p = new JPanel();
        JPanel scegli = new JPanel();
        JPanel torna = new JPanel();
        JPanel vuoto = new JPanel();
        JButton t = new JButton("Torna indietro");

        torna.add(t);
        JLabel s = new JLabel("Scegli Viaggio");

        scegli.add(s);

        cont.add(p);
        cont.add(scegli);
        cont.add(panel);
        cont.add(vuoto);
        cont.add(torna);

        JFrame f = new JFrame();
        f.setTitle("Scegli Viaggio");
        f.add(cont);

        f.setSize(550,400);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setVisible(true);

        if(viaggi.isEmpty()) {
            JLabel indisp  = new JLabel("Non ci sono viaggi disponibili");
            vuoto.add(indisp);
        }
        else{
            for (ViaggioProgrammato viaggio : viaggi) {
                if (viaggio.getPostiDisponibili() == 0) {
                    JLabel indisp = new JLabel("Non ci sono viaggi disponibili");
                    vuoto.add(indisp);
                } else {
                    JPanel v1 = new JPanel();
                    JPanel v2 = new JPanel();
                    JLabel ad = new JLabel(" Citta Partenza : " + viaggio.getAddress().getCitta_partenza() + " Citta Destinazione : " + viaggio.getAddress().getCitta_destinazione() + " Indirizzo Partenza : " + viaggio.getAddress().getInidirizzo_partenza() + " Indirizzo destinazione : " + viaggio.getAddress().getIndirizzo_destinazione() + " Km viaggio : " + viaggio.getAddress().getKm_corsa());
                    JLabel info = new JLabel(" ID : " + viaggio.getID() + " Autista :  " + viaggio.getAutista().getUsername() + " Veicolo :  " + viaggio.getVeicolo().getTarga() + " Data Partenza : " + viaggio.getData_partenza() + " Ora Partenza : " + viaggio.getOra_partenza() + " Evento : " + viaggio.getEvento() + " Prezzo : " + viaggio.getPrezzo() + " Numero posti disponibili : " + viaggio.getPostiDisponibili());
                    JButton j = new JButton(" Prenota Viaggio");
                    JPanel bottoni = new JPanel();
                    bottoni.add(j);
                    v1.add(ad);
                    v2.add(info);
                    panel.add(v1);
                    panel.add(v2);
                    panel.add(bottoni);
                    j.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            controller.inserisciCorsaProgrammata(viaggio, utente);
                            controller.diminuisciPostiDisponibili(viaggio);
                            new MenuUtente(shuttlelive, utente);
                            f.setVisible(false);
                        }
                    });

                }
            }
        }
        t.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CercaViaggioProgrammato(shuttlelive,utente);
                f.setVisible(false);
            }
        });
    }

}