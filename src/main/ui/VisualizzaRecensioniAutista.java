package ui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VisualizzaRecensioniAutista extends javax.swing.JFrame{
    private ShuttleLive shuttlelive;
    private List<Recensione> recensioni;
    private CorseController controller;
    private Utente utente;
    private JPanel elencoautistiPanel;
    private String autista;

    public VisualizzaRecensioniAutista(ShuttleLive sl, CorseController cl,Utente user, List<Recensione> recen,String autist) {//String partenza, String arrivo, Date data_partenza, LocalTime ora_partenza) {
        this.shuttlelive=sl;
        this.recensioni = recen;
        this.controller=cl;
        this.utente=user;
        this.autista=autist;

        //JTable tbautisti = new JTable(0,5);
        //DefaultTableModel modelautisti = (DefaultTableModel) tbautisti.getModel();
        JPanel panel = new JPanel(new GridLayout(0,1));
        JPanel cont = new JPanel();
        JPanel p = new JPanel();
        JPanel scegli = new JPanel();
        JPanel torna = new JPanel();
        JButton t = new JButton("Torna indietro");
        torna.add(t);

        JLabel s = new JLabel("Ecco le recensioni per l'autista");
        JLabel l = new JLabel("autista= " + autista);
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
        f.setTitle("Visualizza Recensioni");
        //f.setContentPane(elencoautistiPanel);
        //f.getContentPane().add(new JScrollPane(tbautisti));
        f.add(cont);

        f.setSize(550,400);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setVisible(true);

        for( Recensione recensione : recensioni) {
            JLabel j = new JLabel(" Recensione : "+recensione.getCommento() + " voto :  " + recensione.getVoto() +  "  " );
            JPanel review = new JPanel();
            review.add(j);
            panel.add(review);


        }
        t.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuUtente(shuttlelive,utente);
                f.setVisible(false);
            }
        });


        //panel.add(new JScrollPane(tbautisti));


    }

}

