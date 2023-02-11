package model;

import SL_db.AutistaDAO;
import SL_db.PatenteDao;
import SL_db.UtenteDAO;
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
    public void inserisciNuovoUtente(String username, String email, String password, String nome, String cognome, String telefono, Date data_nascita) {
        Utente user = new Utente(username,email,password,nome,cognome,telefono,data_nascita);
        System.out.println(user);
        UtenteDAO daouser = new UtenteDAO();
        daouser.insertUtente(user);
    }
    public boolean inserisciPatente(String codice,String autista, Date data_conseguimento,Date data_scadenza,String livello) {
        Patente patent = new Patente(codice,autista,data_conseguimento,data_scadenza,livello);
        System.out.println(patent);
        PatenteDao daopatent = new PatenteDao();
        daopatent.insertPatente(patent);

        return false;

    }





}
