package ui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

import static model.ShuttleLive.shuttlelive;

public class GestisciPrenotazioni {

    private CorseController corsecontroller;
    private Utente utente;

    private ShuttleLive shuttelive;
    public GestisciPrenotazioni(ShuttleLive sl,CorseController cc, Utente user) {
        this.corsecontroller=cc;
        this.shuttelive=sl;
        this.utente=user;

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

        corseviaggi = corsecontroller.caricaCorseViaggiByUtente(utente);
        for(CorsaViaggio corsa : corseviaggi) {
            //System.out.println(corsa);
        }

        if(corseviaggi.isEmpty()) {
            JLabel vuoto = new JLabel("Non ci sono corse prenotate");
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
                    if(v.getData_partenza().before(new Date())) {
                        JButton k = new JButton(" Recensisci");
                        JPanel bottoni = new JPanel();
                        bottoni.add(k);
                        panel.add(bottoni);
                        k.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                //RECENSIONE
                                new InserisciRecensione(shuttlelive,corsecontroller,v,utente);
                                f.setVisible(false);
                            }
                        });
                    } else if(v.getData_partenza().after(new Date())) {
                        JButton y = new JButton(" Annulla prenotazione");
                        JPanel bottoni = new JPanel();
                        bottoni.add(y);
                        panel.add(bottoni);
                        y.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                corsecontroller.cancellaCorsa((Corsa) v,utente);
                                new MenuUtente(shuttlelive, utente);
                                f.setVisible(false);
                            }
                        });
                    }
                } else if (v instanceof ViaggioProgrammato) {

                    JLabel ad = new JLabel(" Citta Partenza : " + v.getAddress().getCitta_partenza() + " Citta Destinazione : " + v.getAddress().getCitta_destinazione() + " Indirizzo Partenza : " + v.getAddress().getInidirizzo_partenza() + " Indirizzo destinazione : " + v.getAddress().getIndirizzo_destinazione() + " Km viaggio : " + v.getAddress().getKm_corsa());
                    JLabel info = new JLabel(" Autista :  " + v.getAutista().getUsername() + " Data Partenza : " + v.getData_partenza() + " Ora Partenza : " + v.getOra_partenza() + " Evento : " + ((ViaggioProgrammato) v).getEvento() + " Prezzo : " + v.getPrezzo());
                    v1.add(info);
                    v2.add(ad);
                    panel.add(v1);
                    panel.add(v2);
                    if(v.getData_partenza().before(new Date())) {
                        JButton j = new JButton(" Recensisci");
                        JPanel bottoni = new JPanel();
                        bottoni.add(j);
                        panel.add(bottoni);
                        j.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                //RECENSIONE
                                new InserisciRecensione(shuttlelive,corsecontroller,v,utente);
                                f.setVisible(false);
                            }
                        });
                    } else if(v.getData_partenza().after(new Date())) {
                        JButton g = new JButton(" Annulla prenotazione");
                        JPanel bottoni = new JPanel();
                        bottoni.add(g);
                        panel.add(bottoni);
                        g.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                corsecontroller.aumentaPostiDisponibili((ViaggioProgrammato) v);
                                corsecontroller.cancellaViaggio((ViaggioProgrammato) v,utente);
                                new MenuUtente(shuttlelive, utente);
                                f.setVisible(false);
                            }
                        });
                    }

                }
            }
        }

        t.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuUtente(shuttlelive,utente);
                f.setVisible(false);
            }
        });
    }
}
