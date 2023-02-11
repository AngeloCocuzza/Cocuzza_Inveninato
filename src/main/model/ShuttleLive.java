package model;

import SL_db.AutistaDAO;
import SL_db.PatenteDao;
import SL_db.VeicoloDao;
import SL_db.UtenteDAO;
import SL_db.VeicoloDao;
import ui.MainForm;

import javax.swing.*;
import java.util.Date;

public class ShuttleLive {
    public static ShuttleLive shuttlelive;

    public static ShuttleLive getInstance() {
        if(shuttlelive == null)
            shuttlelive = new ShuttleLive();
        else
            System.out.println("Istanza già creata");
        return shuttlelive;
    }

    public Autista inserisciNuovoAutista(String username, String email, String password, String nome, String cognome, String telefono, Date data_nascita) {
        Autista user = new Autista(username,email,password,nome,cognome,telefono,data_nascita);
        System.out.println(user);
        AutistaDAO daouser = new AutistaDAO();
        daouser.insertAutista(user);
        return user;
    }
    public Utente inserisciNuovoUtente(String username, String email, String password, String nome, String cognome, String telefono, Date data_nascita) {
        Utente user = new Utente(username,email,password,nome,cognome,telefono,data_nascita);
        System.out.println(user);
        UtenteDAO daouser = new UtenteDAO();
        daouser.insertUtente(user);
        return user;
    }

    public Autista loginAutista(String email,String password) {
        AutistaDAO daouser = new AutistaDAO();
        Autista user = new Autista();
        user = daouser.selectAutista(email,password);
        return user;
    }

    public Utente loginUtente(String email,String password) {
        UtenteDAO daouser = new UtenteDAO();
        Utente user = new Utente();
        user = daouser.selectUtente(email,password);
        return user;
    }
    public boolean inserisciPatente(String codice,Autista autista, Date data_conseguimento,Date data_scadenza,String livello) {
        Patente patent = new Patente(codice,autista,data_conseguimento,data_scadenza,livello);
        System.out.println(patent);
        PatenteDao daopatent = new PatenteDao();
        daopatent.insertPatente(patent);

        return false;}
        public boolean inserisciVeicolo(String targa,Autista autista, String marca,String modello,String colore,Integer n_posti) {
            Veicolo veicol = new Veicolo(targa,autista,marca,modello,colore,n_posti);
            System.out.println(veicol);
            VeicoloDao daoveicol = new VeicoloDao();
            daoveicol.insertVeicolo(veicol);

            return false;

    }





}
