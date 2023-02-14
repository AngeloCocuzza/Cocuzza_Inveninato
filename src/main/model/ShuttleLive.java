package model;

import SL_db.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShuttleLive {
    public static ShuttleLive shuttlelive;

    Utente utenteCorrente;
    Autista autistaCorrente;
    Veicolo veicoloCorrente;

    Patente patenteCorrente;

    Corsa corsaCorrente;

    Disponibilita disponibilitaCorrente;
    List<Autista> autistiDisponibiliCorrente;
    List<Veicolo> veicoliAutistaCorrente;

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

    public Autista loginAutista(String email,String password) throws Exception {
        autistaCorrente = verificaLoginAutista(email,password);
        return autistaCorrente;
    }

    public Utente loginUtente(String email,String password) throws Exception {
        utenteCorrente = verificaLoginUtente(email, password);
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
        veicoloCorrente= verificaCampiVeicolo(targa, autista, marca, modello, colore, n_posti);
        return veicoloCorrente;
    }

    public Disponibilita inserisciNuovaDisponibilita(String autista, Date giorno_disponibilita, LocalTime ora_inizio, LocalTime ora_fine, String citta_partenza) throws Exception {
        disponibilitaCorrente = verificaDisponibilita(autista,giorno_disponibilita,ora_inizio,ora_fine,citta_partenza);
        return disponibilitaCorrente;
    }
    public List<Autista> cercaAutistiDisponibili(String partenza, String arrivo, java.sql.Date data_partenza, LocalTime ora){
        DisponibilitaDAO daodisponibilita=new DisponibilitaDAO();
        List<String> str = new ArrayList<>();
        str=daodisponibilita.selectNomeAutistiDisponibili(partenza,data_partenza,ora);
        AutistaDAO autdao =new AutistaDAO();
        autistiDisponibiliCorrente=autdao.selectAutistaByName(str);
        return autistiDisponibiliCorrente;

    }
    public List<Veicolo> veicoliAutista(String autista){
        VeicoloDao daoveicoli=new VeicoloDao();

        veicoliAutistaCorrente=daoveicoli.allVeicoloAutista(autista);

        return veicoliAutistaCorrente;

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

        List<Veicolo> allveicolo = new ArrayList<Veicolo>();
        allveicolo = daoveicol.allVeicolo();
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

    public Autista verificaLoginAutista(String email, String password) throws Exception {
        AutistaDAO daouser = new AutistaDAO();
        Autista user = new Autista();
        user = daouser.selectAutista(email, password);
        if (password.length() <= 7) {
            throw new Exception("password troppo breve");
        } else {
            if (user.getPassword() == null || user.getEmail()==null) {
                throw new Exception("autista non trovato");
            }
            return user;
        }
    }

    public Utente verificaLoginUtente(String email, String password) throws Exception {
        UtenteDAO daouser = new UtenteDAO();
        Utente user = new Utente();
        if (password.length() <= 7) {
            throw new Exception("password troppo breve");
        } else {
            user = daouser.selectUtente(email, password);
            if (user.getPassword() == null || user.getEmail()==null) {
                throw new Exception("utente non trovato");
            }
            return user;
        }
    }

    public Disponibilita verificaDisponibilita(String autista, Date giorno_disponibilita, LocalTime ora_inizio, LocalTime ora_fine, String citta_partenza) throws Exception {
        DisponibilitaDAO dispdao = new DisponibilitaDAO();
        List<Disponibilita> alldisp = new ArrayList<>();
        alldisp = dispdao.allDisponibilita();
        if(giorno_disponibilita.before(new Date())) {
            throw new Exception("data non valida");
        } else {
            for (Disponibilita disp : alldisp) {
                if(disp.getGiorno_disponibilita().equals(giorno_disponibilita) == true && disp.getAutista().equals(autista) == true) {
                    System.out.println("è già presente una messa a disposizione per la data inserita");
                    throw new Exception("data già occupata");
                }
            }
            Disponibilita dispo = new Disponibilita(autista, giorno_disponibilita, ora_inizio, ora_fine, citta_partenza);
            dispdao.insertDisponibilita(dispo);
            return dispo;
        }
    }

    public Utente getUtenteCorrente() {return utenteCorrente;}
    public Autista getAutistaCorrente() {return autistaCorrente;}

    public Patente getPatenteCorrente() {return patenteCorrente;}
    public Veicolo getVeicoloCorrente() {return veicoloCorrente;}

    public Disponibilita getDisponibilitaCorrente() {return disponibilitaCorrente;}

    public Corsa getCorsaCorrente() {return corsaCorrente;}
}
