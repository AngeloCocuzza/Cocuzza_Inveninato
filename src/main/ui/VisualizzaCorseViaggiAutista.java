package ui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static model.ShuttleLive.shuttlelive;

public class VisualizzaCorseViaggiAutista {
    private CorseController corsecontroller;
    private Autista autista;

    private ShuttleLive shuttelive;
    public VisualizzaCorseViaggiAutista(ShuttleLive sl,CorseController cc, Autista auti) {
        this.corsecontroller=cc;
        this.shuttelive=sl;
        this.autista=auti;

        JPanel panel = new JPanel(new GridLayout(0,1));
        JPanel cont = new JPanel();
        JPanel torna = new JPanel();
        JPanel p = new JPanel();
        JButton t = new JButton("Torna indietro");
        JLabel s = new JLabel("Prenotazioni effettuate");

        torna.add(t);


        cont.add(panel);
        cont.add(torna);


        JFrame f = new JFrame();
        f.setTitle("Scegli Viaggio");
        f.add(cont);

        f.setSize(550,400);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setVisible(true);

        List<CorsaViaggio> corseviaggi = new ArrayList<>();

        corseviaggi = corsecontroller.caricaCorseViaggiByAutista(autista);

        if(corseviaggi.isEmpty()) {
            JLabel vuoto = new JLabel("Non ci sono corse");
            panel.add(vuoto);
        } else {
            for (CorsaViaggio v : corseviaggi) {
                JPanel v1 = new JPanel();
                JPanel v2 = new JPanel();
                if(v instanceof Corsa) {
                    JLabel l = new JLabel( "Autista : " + v.getAutista().getUsername() + " Utente : " + ((Corsa) v).getUtente().getUsername() + " Veicolo : " + v.getVeicolo().getTarga() + " Prezzo : " + ((Corsa) v).getFee());
                    JLabel d = new JLabel(" Data partenza = " + v.getData_partenza() + " Tratta : " + v.getAddress() + " Ora partenza : " + v.getOra_partenza() );
                    v1.add(l);
                    v2.add(d);
                    panel.add(v1);
                    panel.add(v2);
                } else if (v instanceof ViaggioProgrammato) {
                    JLabel ad = new JLabel(" Citta Partenza : " + v.getAddress().getCitta_partenza() + " Citta Destinazione : " + v.getAddress().getCitta_destinazione() + " Indirizzo Partenza : " + v.getAddress().getInidirizzo_partenza() + " Indirizzo destinazione : " + v.getAddress().getIndirizzo_destinazione() + " Km viaggio : " + v.getAddress().getKm_corsa());
                    JLabel info = new JLabel(" Autista :  " + v.getAutista().getUsername() + " Data Partenza : " + v.getData_partenza() + " Ora Partenza : " + v.getOra_partenza() + " Evento : " + ((ViaggioProgrammato) v).getEvento() + " Prezzo : " + v.getPrezzo());
                    v1.add(info);
                    v2.add(ad);
                    panel.add(v1);
                    panel.add(v2);

                }
            }
        }

        t.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuAutista(shuttlelive,autista);
                f.setVisible(false);
            }
        });
    }
}
