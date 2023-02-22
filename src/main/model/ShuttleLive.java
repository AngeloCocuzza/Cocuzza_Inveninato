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
    List<ViaggioProgrammato> viaggicorrente;
    Veicolo veicolocorrente;
    Utente utentecorrente;
    Autista autistacorrente;

    public static ShuttleLive getInstance() {
        if(shuttlelive == null)
            shuttlelive = new ShuttleLive();
        else
            System.out.println("Istanza già creata");
        return shuttlelive;
    }


    public Autista inserisciNuovoAutista(String username, String email, String password,String nome,String cognome,String telefono,Date data_nascita) throws Exception {
        Autista autista = new Autista(username,email,password,nome,cognome,telefono,data_nascita);
        autistaCorrente = verificaCampiAutista(autista);
        //System.out.println(autistaCorrente);
        return autistaCorrente;
    }
    public Utente inserisciNuovoUtente(String username, String email, String password,String nome,String cognome,String telefono,Date data_nascita) throws Exception {
        Utente utente = new Utente(username,email,password,nome,cognome,telefono,data_nascita);
        utenteCorrente = verificaCampiUtente(utente);
        return utenteCorrente;
    }

    public Autista loginAutista(String email,String password) throws Exception {
        autistaCorrente = verificaLoginAutista(email,password);
        caricaDati(autistaCorrente);
        return autistaCorrente;
    }

    public void caricaDati(Autista autista) {
        if(!Facade.getInstance().tuttiVeicoloAutista(autista.getUsername()).isEmpty()) {
            autista.setVeicoli(Facade.getInstance().tuttiVeicoloAutista(autista.getUsername()));
        }
        if(Facade.getInstance().trovaPatenteDaAutista(autista.getUsername()) != null) {
            autista.setPatente(Facade.getInstance().trovaPatenteDaAutista(autista.getUsername()));
        }
        if(!Facade.getInstance().caricaDisponibilitaAutista(autista.getUsername()).isEmpty()) {
            autista.setDisponibilita(Facade.getInstance().caricaDisponibilitaAutista(autista.getUsername()));
        }
    }

    public Utente loginUtente(String email,String password) throws Exception {
        utenteCorrente = verificaLoginUtente(email, password);
        return utenteCorrente;
    }
    public Patente inserisciPatente(String codice,Date data_condeguimento, Date data_scadenza,String livello,Autista autista) throws Exception {
        Patente patente = new Patente(codice,data_condeguimento,data_scadenza,livello);
        patenteCorrente = verificaPatente(patente,autista);
        return patenteCorrente;

    }
    public Patente verificaPatente(Patente patente,Autista autista) throws Exception {
        if(patente.getCodice().equals((""))||patente.getData_conseguimento()==null||patente.getData_scadenza()==null||patente.getLivello().equals(""))
            throw new Exception("riempire tutti i campi");

            Facade.getInstance().salvaPatente(patente,autista);
            autista.setPatente(patente);
            patenteCorrente=patente;
            return patenteCorrente;
    }
    public Veicolo inserisciVeicolo(String targa,String marca, String modello, String colore, Integer n_posti,Autista autista) throws Exception {
        Veicolo veicolo = new Veicolo(targa,marca,modello,colore,n_posti);
        veicoloCorrente= verificaCampiVeicolo(veicolo,autista);
        return veicoloCorrente;
    }

    public Disponibilita inserisciNuovaDisponibilita(Autista autista,Date giorno_disp, LocalTime ora_inizio,LocalTime ora_fine,String citta_partenza) throws Exception {
        Disponibilita disp = new Disponibilita(giorno_disp,ora_inizio,ora_fine,citta_partenza);
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

    public Veicolo verificaCampiVeicolo(Veicolo veicolo,Autista autista) throws Exception {

        ////passare sia veicolo che autista e farle come disponibilita
        Map<String,Veicolo> allveicolo = new HashMap<>();
        allveicolo = Facade.getInstance().tuttiVeicolo();

        if(veicolo.getMarca().equals("") || veicolo.getModello().equals("") || veicolo.getColore().equals("") || veicolo.getN_posti()==null) {
            System.out.println("riempire");
            throw new Exception("riempire tutti i campi");
        }
        if(veicolo.getN_posti()<0) {
            System.out.println("posti negativi");
            throw new Exception("il numero dei posti non può essere negativo");
        }
        if (veicolo.getTarga().length() != 7) {
            System.out.println("la targa deve essere di 7 caratteri");
            throw new Exception("targa non valida");
        } else {
            if(!allveicolo.isEmpty()) {
                for (Veicolo veico : allveicolo.values()) {
                    if (veico.getTarga().equals(veicolo.getTarga()) == true) {
                        System.out.println("targa già presente");
                        throw new Exception("veicolo già registrato");
                    }
                }
            }
            Map<String,Veicolo> veicoli = new HashMap<>();
            if(autista.getVeicoli()==null){
                veicoli.put(veicolo.getTarga(),veicolo);
                autista.setVeicoli(veicoli);
                Facade.getInstance().inserisciVeicolo(veicolo, autista);
            } else {
                autista.setVeicolo(veicolo);
                System.out.println(autista.getVeicoli());
                Facade.getInstance().inserisciVeicolo(veicolo, autista);
            }
        }
        return veicolo;
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
            autista.setDisponibilitaS(disp);
            Facade.getInstance().salvaDisponibilita(disp, autista);
            return disp;
        }
    }


    public Autista autistaSingoloByName(String autista) {
        autistacorrente= CorseController.getInstance().autistaSingoloByName(autista);
        return autistacorrente;
    }

    public Utente utenteSingoloByName(String utente) {
        utentecorrente= CorseController.getInstance().utenteSingoloByName(utente);
        return utentecorrente;
    }

    public Veicolo veicoloSingoloByName(String veicolo) {
        veicolocorrente= CorseController.getInstance().veicoloSingoloByName(veicolo);
        return veicolocorrente;
    }

    public List<ViaggioProgrammato> selezionaViaggioProgrammato(String evento, java.sql.Date data_partenza) throws Exception {
        viaggicorrente = CorseController.getInstance().selezionaViaggioProgrammato(evento,data_partenza);
        return viaggicorrente;
    }

    public void inserisciCorsaProgrammata(ViaggioProgrammato viaggio,Utente user) {
        CorseController.getInstance().inserisciCorsaProgrammata(viaggio,user);
    }
    public void inserisciRecensione(CorsaViaggio viaggio,Integer voto, String commento) throws Exception {
        CorseController.getInstance().inserisciRecensione(viaggio,voto,commento);

    }
    public List<Recensione> selezionaRecensioniAutista(String autista) {
        List<Recensione> recensioni=CorseController.getInstance().selezionaRecensioniAutista(autista);
        return recensioni;

    }

    public void diminuisciPostiDisponibili(ViaggioProgrammato viaggio){
        CorseController.getInstance().diminuisciPostiDisponibili(viaggio);
    }

    public void aumentaPostiDisponibili(ViaggioProgrammato viaggio){
        CorseController.getInstance().aumentaPostiDisponibili(viaggio);
    }
    public List<CorsaViaggio> caricaCorseViaggiByUtente(Utente user) {
        List<CorsaViaggio> corseviaggi = CorseController.getInstance().caricaCorseViaggiByUtente(user);
        return corseviaggi;
    }

    public List<CorsaViaggio> caricaCorseViaggiByAutista(Autista autista) {
        List<CorsaViaggio> corseviaggi = CorseController.getInstance().caricaCorseViaggiByAutista(autista);
        return corseviaggi;
    }

    public void cancellaCorsa(Corsa corsa,Utente user) {
        CorseController.getInstance().cancellaCorsa(corsa,user);
    }

    public void cancellaViaggio(ViaggioProgrammato viaggio, Utente user) {
        CorseController.getInstance().cancellaViaggio(viaggio,user);
    }

    public Corsa creaCorsa(Veicolo veicolo, Autista autista, Utente utente, String c_partenza, String c_arrivo, java.util.Date data_partenza, LocalTime ora_partenza, String indirizzopart, String indirizzodest) {
        Corsa corsa = CorseController.getInstance().creaCorsa(veicolo,autista,utente,c_partenza,c_arrivo,data_partenza,ora_partenza,indirizzopart,indirizzodest);
        return corsa;
    }

    public Corsa inserisciCorsa(Corsa corsa) throws Exception {
        corsaCorrente=CorseController.getInstance().inserisciCorsa(corsa);
        return corsaCorrente;
    }
    public ViaggioProgrammato inserisciViaggio(Autista autista, String targa, String evento, Float prezzo, LocalTime ora_partenza, java.util.Date data_partenza, String citta_partenza, String citta_arrivo, String ind_partenza, String ind_arrivo, Integer km_corsa) throws Exception {
        viaggioCorrente = CorseController.getInstance().inserisciViaggio(autista,targa,evento,prezzo,ora_partenza,data_partenza,citta_partenza,citta_arrivo,ind_partenza,ind_arrivo,km_corsa);
        return viaggioCorrente;
    }

    public Utente getUtenteCorrente() {return utenteCorrente;}
    public Autista getAutistaCorrente() {return autistaCorrente;}

    public Patente getPatenteCorrente() {return patenteCorrente;}
    public Veicolo getVeicoloCorrente() {return veicoloCorrente;}

    public Disponibilita getDisponibilitaCorrente() {return disponibilitaCorrente;}

    public Corsa getCorsaCorrente() {return corsaCorrente;}
    public ViaggioProgrammato getViaggioCorrente() {return viaggioCorrente;}

}
