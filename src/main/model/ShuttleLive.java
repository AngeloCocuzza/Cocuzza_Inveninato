package model;

import SL_db.AutistaDAO;
import SL_db.PatenteDao;
import SL_db.VeicoloDao;
import SL_db.UtenteDAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShuttleLive {
    public static ShuttleLive shuttlelive;

    Utente utenteCorrente;
    Autista autistaCorrente;
    Patente patenteCorrente;

    public static ShuttleLive getInstance() {
        if(shuttlelive == null)
            shuttlelive = new ShuttleLive();
        else
            System.out.println("Istanza già creata");
        return shuttlelive;
    }

    public Autista inserisciNuovoAutista(String username, String email, String password, String nome, String cognome, String telefono, java.sql.Date data_nascita) throws Exception {
        autistaCorrente = verificaCampiAutista(username, email, password, nome, cognome, telefono, data_nascita);
        System.out.println(autistaCorrente);
        return autistaCorrente;
    }
    public Utente inserisciNuovoUtente(String username, String email, String password, String nome, String cognome, String telefono, Date data_nascita) throws Exception {
        utenteCorrente = verificaCampiUtente(username, email, password, nome, cognome, telefono, data_nascita);
        return utenteCorrente;
    }

    public Autista loginAutista(String email,String password) {
        AutistaDAO daouser = new AutistaDAO();
        //Autista user = new Autista();
        autistaCorrente = daouser.selectAutista(email,password);
        return autistaCorrente;
    }

    public Utente loginUtente(String email,String password) {
        UtenteDAO daouser = new UtenteDAO();
        //Utente user = new Utente();
        utenteCorrente = daouser.selectUtente(email,password);
        return utenteCorrente;
    }
    public Patente inserisciPatente(String codice, String autista, Date data_conseguimento, Date data_scadenza, String livello) {
        Patente patent = new Patente(codice,autista,data_conseguimento,data_scadenza,livello);
        System.out.println(patent);
        PatenteDao daopatent = new PatenteDao();
        patenteCorrente = daopatent.insertPatente(patent);
        return patenteCorrente;

    }
    public Veicolo inserisciVeicolo(String targa,String autista, String marca,String modello,String colore,Integer n_posti) throws Exception {
        return verificaCampiVeicolo(targa, autista, marca, modello, colore, n_posti);
    }

    public Utente verificaCampiUtente(String username, String email, String password,String nome, String cognome, String telefono, Date data_nascita) throws Exception {
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

    public Autista verificaCampiAutista(String username, String email, String password,String nome, String cognome, String telefono, Date data_nascita) throws Exception {
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

    public Veicolo verificaCampiVeicolo(String targa,String autista, String marca,String modello,String colore,Integer n_posti) throws Exception {
        VeicoloDao daoveicol = new VeicoloDao();
        daoveicol.allVeicolo();
        List<Veicolo> allveicolo = new ArrayList<Veicolo>();
        if (targa.length() != 7) {
            System.out.println("la targa deve essere di 7 caratteri");
            throw new Exception("targa non valida");
        } else {
            for (Veicolo veicolo : allveicolo) {
                if (veicolo.getTarga().equals(targa) == true) {
                    System.out.println("targa già presente");
                    throw new Exception("veicolo già registrato");

                }
            }
            Veicolo veicol = new Veicolo(targa,autista,marca,modello,colore,n_posti);
            System.out.println(veicol);
            daoveicol.insertVeicolo(veicol);
            return veicol;
        }
    }

    public Utente getUtenteCorrente() {return utenteCorrente;}
    public Autista getAutistaCorrente() {return autistaCorrente;}

    public Patente getPatenteCorrente() {return patenteCorrente;}
}
