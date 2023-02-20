package model;

import SL_db.*;

import java.time.LocalTime;
import java.util.*;

import static SL_db.Facade.facade;

public class ShuttleLive {
    public static ShuttleLive shuttlelive;

    Utente utenteCorrente;
    Autista autistaCorrente;
    Veicolo veicoloCorrente;

    List<Autista> autisti;
    Map<String,Veicolo> veicoli;
    Map<String,Utente> utenti;


    Patente patenteCorrente;
    Corsa corsaCorrente;
    ViaggioProgrammato viaggioCorrente;

    Disponibilita disponibilitaCorrente;
    List<Autista> autistiDisponibiliCorrente;
    Map<String,Veicolo> veicoliAutistaCorrente;

    public static ShuttleLive getInstance() {
        if(shuttlelive == null)
            shuttlelive = new ShuttleLive();
        else
            System.out.println("Istanza già creata");
        return shuttlelive;
    }


    public Autista inserisciNuovoAutista(Autista autista) throws Exception {
        autistaCorrente = verificaCampiAutista(autista);
        System.out.println(autistaCorrente);
        return autistaCorrente;
    }
    public Utente inserisciNuovoUtente(Utente utente) throws Exception {
        utenteCorrente = verificaCampiUtente(utente);
        return utenteCorrente;
    }

    public Autista loginAutista(String email,String password) throws Exception {
        autistaCorrente = verificaLoginAutista(email,password);
        System.out.println(autistaCorrente);
        caricaDati(autistaCorrente);
        return autistaCorrente;
    }

    public void caricaDati(Autista autista) {
        PatenteDao patedao = new PatenteDao();
        VeicoloDao veicdao = new VeicoloDao();
        DisponibilitaDAO dispdao = new DisponibilitaDAO();
        if(!veicdao.allVeicoloAutista(autista.getUsername()).isEmpty()) {
            autista.setVeicoli(veicdao.allVeicoloAutista(autista.getUsername()));
            System.out.println(autista);

        }
        if(Facade.getInstance().trovaPatenteDaAutista(autista.getUsername()) != null) {
            autista.setPatente(Facade.getInstance().trovaPatenteDaAutista(autista.getUsername()));
            System.out.println(autista);
        }
        if(!Facade.getInstance().caricaDisponibilitaAutista(autista.getUsername()).isEmpty()) {
            autista.setDisponibilita(Facade.getInstance().caricaDisponibilitaAutista(autista.getUsername()));
            System.out.println(autista);
        }
        System.out.println(autista);
    }

    public Utente loginUtente(String email,String password) throws Exception {
        utenteCorrente = verificaLoginUtente(email, password);
        return utenteCorrente;
    }
    public Patente inserisciPatente(Patente patente) throws Exception {
        Patente patent = new Patente(patente.getCodice(),patente.getAutista(),patente.getData_conseguimento(),patente.getData_scadenza(), patente.getLivello());
        System.out.println(patent);
        patenteCorrente = verificaPatente(patent);
        return patenteCorrente;

    }
    public Patente verificaPatente(Patente patente) throws Exception {
        if(patente.getCodice().equals((""))||patente.getData_conseguimento()==null||patente.getData_scadenza()==null||patente.getLivello().equals(""))
            throw new Exception("riempire tutti i campi");
            //PatenteDao daopatent = new PatenteDao();

            Facade.getInstance().salvaPatente(patente);
            patenteCorrente=patente;
            return patenteCorrente;

    }
    public Veicolo inserisciVeicolo(Veicolo veicolo) throws Exception {
        veicoloCorrente= verificaCampiVeicolo(veicolo);
        return veicoloCorrente;
    }

    public Disponibilita inserisciNuovaDisponibilita(Autista autista,Disponibilita disp) throws Exception {
        disponibilitaCorrente = verificaDisponibilita(autista, disp);
        return disponibilitaCorrente;
    }
    public List<Autista> cercaAutistiDisponibili(String partenza, String arrivo, java.sql.Date data_partenza, LocalTime ora) throws Exception {

        List<Autista> autisti = new ArrayList<>();
        if(data_partenza.before(new Date())) {
            throw new Exception("data non valida");
        }
        autisti=Facade.getInstance().caricaAutistiDisponibili(partenza,data_partenza,ora);
        autistiDisponibiliCorrente=autisti;
        return autistiDisponibiliCorrente;

    }

    public Corsa inserisciCorsa(Corsa corsa) throws Exception {
        corsaCorrente=verificaCampiCorsa(corsa);

        return corsaCorrente;
    }
    public ViaggioProgrammato inserisciViaggio(ViaggioProgrammato viaggio) throws Exception {
        viaggioCorrente = verificaCampiViaggio(viaggio);
        return viaggioCorrente;
    }

    public ViaggioProgrammato verificaCampiViaggio(ViaggioProgrammato corsa) throws Exception {


             if (corsa.getEvento()==("")||corsa.getAddress().getCitta_destinazione().equals("") || corsa.getAddress().getCitta_partenza().equals("") || corsa.getAddress().getIndirizzo_destinazione().equals("") || corsa.getAddress().getInidirizzo_partenza().equals("") || (corsa.getData_partenza() == null) || (corsa.getOra_partenza() == null) || (corsa.getAddress().getKm_corsa() == null)) {
                throw new Exception("riempire tutti i campi");
             }

             ViaggioProgrammatoDAO viaggiodao=new ViaggioProgrammatoDAO();
             ViaggioProgrammato viag = new ViaggioProgrammato();
             viag=corsa;
             Facade.getInstance().inserisciViaggioPro(viag);
             return viag;


        }



    public Corsa verificaCampiCorsa(Corsa corsa) throws Exception {
        if (corsa.getAddress().getCitta_destinazione().equals("") || corsa.getAddress().getCitta_partenza().equals("") || corsa.getAddress().getIndirizzo_destinazione().equals("") || corsa.getAddress().getInidirizzo_partenza().equals("") || corsa.getData_partenza() == null || corsa.getOra_partenza() == null) {
            throw new Exception("riempire tutti i campi");
    }
        //CorsaDAO corsadao = new CorsaDAO();
        Corsa cr = new Corsa();
        cr=corsa;
        Facade.getInstance().insCorsa(cr);
        //corsadao.insertCorsa(cr);
        return corsa;
    }

    public Map<String,Veicolo> veicoliAutista(String autista){
        //VeicoloDao daoveicoli=new VeicoloDao();
        veicoliAutistaCorrente=Facade.getInstance().tuttiVeicoloAutista(autista);
                //daoveicoli.allVeicoloAutista(autista);
        return veicoliAutistaCorrente;

    }

    public Utente verificaCampiUtente(Utente user) throws Exception {

        List<Utente> allUsers = new ArrayList<>();
        allUsers = Facade.getInstance().caricaUtenti();
        if(user.getUsername().equals("") || user.getEmail().equals("") || user.getPassword().equals("") || user.getNome().equals("") || user.getCognome().equals("") || user.getTelefono().equals("") || user.getData_nascita()==null) {
            throw new Exception("riempire tutti i campi");
        }
        if (user.getPassword().length() <= 7) {
            System.out.println("la password deve avere almeno 8 caratteri");
            throw new Exception("password troppo corta");
        } else {
            for (Utente utente : allUsers) {
                if (utente.getUsername().equals(user.getUsername()) || utente.getEmail().equals(user.getEmail())) {
                    System.out.println("email o username già presenti");
                    throw new Exception("email o username già in uso");
                }
            }
            System.out.println(user);
            Facade.getInstance().salvaUtente(user);
            return user;

        }
    }

    public Autista verificaCampiAutista(Autista autista) throws Exception {

        List<Autista> allAutisti = new ArrayList<>();
        allAutisti = Facade.getInstance().caricaAutisti();
        if(autista.getUsername().equals("") || autista.getEmail().equals("") || autista.getPassword().equals("") || autista.getNome().equals("") || autista.getCognome().equals("") || autista.getTelefono().equals("") || autista.getData_nascita()==null) {
            throw new Exception("riempire tutti i campi");
        }
        if (autista.getPassword().length() <= 7) {
            System.out.println("la password deve avere almeno 8 caratteri");
            throw new Exception("password troppo corta");
        } else {
            for (Autista auti : allAutisti) {
                if (auti.getUsername().equals(autista.getUsername()) == true || auti.getEmail().equals(autista.getEmail()) == true) {
                    System.out.println("email o username già presenti");
                    throw new Exception("email o username già in uso");

                }
            }
            System.out.println(autista);
            Facade.getInstance().salvaAutista(autista);
            return autista;
        }
    }

    public Veicolo verificaCampiVeicolo(Veicolo veicolo) throws Exception {

        //VeicoloDao daoveicol = new VeicoloDao();

        Map<String,Veicolo> allveicolo = new HashMap<>();
        allveicolo = Facade.getInstance().tuttiVeicoloAutista();
                //daoveicol.allVeicolo();
        if(veicolo.getMarca().equals("") || veicolo.getModello().equals("") || veicolo.getColore().equals("") || veicolo.getN_posti()==null) {
            throw new Exception("riempire tutti i campi");
        }
        if(veicolo.getN_posti()<0) {
            throw new Exception("il numero dei posti non può essere negativo");
        }
        if (veicolo.getTarga().length() != 7) {
            System.out.println("la targa deve essere di 7 caratteri");
            throw new Exception("targa non valida");
        } else {
            for (Veicolo veico : allveicolo.values()) {
                if (veico.getTarga().equals(veicolo.getTarga()) == true) {
                    System.out.println("targa già presente");
                    throw new Exception("veicolo già registrato");
                }
            }
            Veicolo veicol = veicolo;
            System.out.println(veicol);
            Facade.getInstance().inserisciVeicolo(veicol);
            //aoveicol.insertVeicolo(veicol);
            return veicol;
        }
    }

    public Autista verificaLoginAutista(String email, String password) throws Exception {
        Autista user = new Autista();
        user = Facade.getInstance().trovaAutista(email, password);
        if(email.equals("") || password.equals("")) {
            throw new Exception("riempire tutti i campi");
        }
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

        Utente user = new Utente();
        if(email.equals("") || password.equals("")) {
            throw new Exception("riempire tutti i campi");
        }
        if (password.length() <= 7) {
            throw new Exception("password troppo breve");
        } else {
            user = Facade.getInstance().trovaUtente(email, password);
            if (user.getPassword() == null || user.getEmail()==null) {
                throw new Exception("utente non trovato");
            }
            return user;
        }
    }

    public Disponibilita verificaDisponibilita(Autista autista,Disponibilita disp) throws Exception {

        List<Disponibilita> alldisp = new ArrayList<>();
        alldisp = Facade.getInstance().caricaDisponibilita();
        if(disp.getGiorno_disponibilita()==null || disp.getCitta_partenza().equals("") ) {
            throw new Exception("riempire tutti i campi");
        }
        if(disp.ora_inizio.getHour() >23 || disp.getOra_fine().getHour() >23 || disp.getOra_inizio().getHour()<0 || disp.getOra_fine().getHour()<0) {
            throw new Exception("ora non valida");
        }
        if(disp.getOra_inizio().getMinute() >59 || disp.getOra_fine().getMinute() >59 || disp.getOra_inizio().getMinute()<0 || disp.getOra_fine().getMinute() >59) {
            throw new Exception("minuto non valida");
        }
        if(disp.getGiorno_disponibilita().before(new Date())) {
            throw new Exception("data non valida");
        } else {
            for (Disponibilita dispon : alldisp) {
                if(dispon.getGiorno_disponibilita().equals(disp.getGiorno_disponibilita()) == true ) {
                    System.out.println("è già presente una messa a disposizione per la data inserita");
                    throw new Exception("data già occupata");
                }
            }
            Disponibilita dispo = disp;
            Facade.getInstance().salvaDisponibilita(dispo, autista);
            return dispo;
        }
    }

    public Utente getUtenteCorrente() {return utenteCorrente;}
    public Autista getAutistaCorrente() {return autistaCorrente;}

    public Patente getPatenteCorrente() {return patenteCorrente;}
    public Veicolo getVeicoloCorrente() {return veicoloCorrente;}

    public Disponibilita getDisponibilitaCorrente() {return disponibilitaCorrente;}

    public Corsa getCorsaCorrente() {return corsaCorrente;}
    public ViaggioProgrammato getViaggioCorrente() {return viaggioCorrente;}

}
