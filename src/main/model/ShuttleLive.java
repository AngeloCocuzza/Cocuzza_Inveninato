package model;

import SL_db.AutistaDAO;
import SL_db.PatenteDao;
import SL_db.VeicoloDao;
import SL_db.UtenteDAO;
import SL_db.VeicoloDao;
import ui.MainForm;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShuttleLive {
    public static ShuttleLive shuttlelive;

    public static ShuttleLive getInstance() {
        if(shuttlelive == null)
            shuttlelive = new ShuttleLive();
        else
            System.out.println("Istanza già creata");
        return shuttlelive;
    }

    public Autista inserisciNuovoAutista(String username, String email, String password, String nome, String cognome, String telefono, Date data_nascita) throws Exception {
        AutistaDAO daoautista = new AutistaDAO();
        List<Autista> allAutisti = new ArrayList<Autista>();
        allAutisti = daoautista.allAutisti();
        if (password.length() <= 7) {
            System.out.println("la password deve avere almeno 8 caratteri");
            throw new Exception("password troppo corta");
        } else {
            for (Autista autista : allAutisti) {
                if (autista.getUsername().equals(username) == true || autista.getEmail().equals(email) == true) {
                    System.out.println("email o username già presenti");
                    throw new Exception("email o username già in uso");

                }
            }
            Autista autista = new Autista(username, email, password, nome, cognome, telefono, data_nascita);
            System.out.println(autista);
            daoautista.insertAutista(autista);
            return autista;

        }
    }
    public Utente inserisciNuovoUtente(String username, String email, String password, String nome, String cognome, String telefono, Date data_nascita) throws Exception {
        UtenteDAO daouser = new UtenteDAO();
        List<Utente> allUsers = new ArrayList<Utente>();
        allUsers = daouser.allUtente();
        if (password.length() <= 7) {
            System.out.println("la password deve avere almeno 8 caratteri");
            throw new Exception("password troppo corta");
        } else {
            for (Utente utente : allUsers) {
                if (utente.getUsername().equals(username) == true || utente.getEmail().equals(email) == true) {
                    System.out.println("email o username già presenti");
                    throw new Exception("email o username già in uso");

                }
            }
            Utente user = new Utente(username, email, password, nome, cognome, telefono, data_nascita);
            System.out.println(user);
            daouser.insertUtente(user);
            return user;

        }
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
    public void inserisciPatente(String codice,Autista autista, Date data_conseguimento,Date data_scadenza,String livello) {
        Patente patent = new Patente(codice,autista,data_conseguimento,data_scadenza,livello);
        System.out.println(patent);
        PatenteDao daopatent = new PatenteDao();
        daopatent.insertPatente(patent);

    }
    public void inserisciVeicolo(String targa,Autista autista, String marca,String modello,String colore,Integer n_posti) {
        Veicolo veicol = new Veicolo(targa,autista,marca,modello,colore,n_posti);
        System.out.println(veicol);
        VeicoloDao daoveicol = new VeicoloDao();
        daoveicol.insertVeicolo(veicol);

    }

}
