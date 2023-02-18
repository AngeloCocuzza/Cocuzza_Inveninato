package model;

import SL_db.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CorseController {

    Utente utentecorrente;

    Autista autistacorrente;

    Veicolo veicolocorrente;

    List<ViaggioProgrammato> viaggicorrente;

    Map<String,CorsaViaggio> allviaggi;

    public Utente getUtentecorrente() {
        return utentecorrente;
    }

    public Autista getAutistacorrente() {
        return autistacorrente;
    }

    public Veicolo getVeicolocorrente() {
        return veicolocorrente;
    }

    public List<ViaggioProgrammato> getViaggicorrente() {
        return viaggicorrente;
    }

    public static CorseController corsecontr;
    public static CorseController getInstance() {
        if(corsecontr == null)
            corsecontr = new CorseController();
        else
            System.out.println("Istanza già creata");
        return corsecontr;
    }

    public Autista autistaSingoloByName(String autista) {
        AutistaDAO autidao = new AutistaDAO();
        autistacorrente= autidao.selectAutistaSingoloByName(autista);
        return autistacorrente;
    }

    public Utente utenteSingoloByName(String utente) {
        UtenteDAO userdao = new UtenteDAO();
        utentecorrente= userdao.selectUtenteSingoloByName(utente);
        return utentecorrente;
    }

    public Veicolo veicoloSingoloByName(String veicolo) {
        VeicoloDao veicdao = new VeicoloDao();
        veicolocorrente= veicdao.allvVeicoloTarga(veicolo);
        return veicolocorrente;
    }

    public List<ViaggioProgrammato> selezionaViaggioProgrammato(String evento, Date data_partenza) throws Exception {
        viaggicorrente = verificaCampiViaggiProgrammato(evento, data_partenza);
        return viaggicorrente;
    }

    public void inserisciCorsaProgrammata(ViaggioProgrammato viaggio,Utente user) {
        ViaggioProgrammatoDAO viaggiodao = new ViaggioProgrammatoDAO();
        List<Utente> utenti = viaggio.getUtentiPrenotati();
        utenti.add(user);
        viaggio.setUtentiPrenotati(utenti);
        System.out.println("Lista" + utenti);
        viaggiodao.insertCorsaProgrammata(viaggio.getID(),user.getUsername());
    }
    public void inserisciRecensione(CorsaViaggio viaggio) {
        if(viaggio instanceof Corsa){
        RecensioneCorsaDAO recensionedao = new RecensioneCorsaDAO();
        recensionedao.insertRecensione(viaggio);}
        else if(viaggio instanceof ViaggioProgrammato){
            RecensioneViaggioDAO recensionedao = new RecensioneViaggioDAO();
            recensionedao.insertRecensione(viaggio);

        }

    }
    public List<Recensione> inserisciRecensione(String autista) {
        List<Recensione> recensioni=new ArrayList<>();

            RecensioneCorsaDAO recensionecorsadao = new RecensioneCorsaDAO();
            recensioni=recensionecorsadao.allRecensioniAutista(autista);

            RecensioneViaggioDAO recensioneviaggiodao = new RecensioneViaggioDAO();
            recensioni.addAll(recensioneviaggiodao.allRecensioniAutista(autista));

        }





    public List<ViaggioProgrammato> verificaCampiViaggiProgrammato(String evento, java.sql.Date data_partenza) throws Exception {
        ViaggioProgrammatoDAO viaggidao = new ViaggioProgrammatoDAO();
        List<ViaggioProgrammato> viaggi = new ArrayList();

        if(evento.equals("") || data_partenza==null) {
            throw new Exception("riempire tutti i campi");
        }
        if(data_partenza.before(new java.util.Date())) {
            throw new Exception("data non valida");
        }
        viaggi = viaggidao.selectViaggiByEventoOrData(evento,data_partenza);
        return viaggi;

    }

    public void diminuisciPostiDisponibili(ViaggioProgrammato viaggio){
        ViaggioProgrammatoDAO viaggiodao = new ViaggioProgrammatoDAO();
        viaggio.setPostiDisponibili(viaggio.getVeicolo().getN_posti()-1);
        viaggiodao.updatePostiDisponibili(viaggio.getID());
    }

    public Map<String,CorsaViaggio> caricaCorseViaggiByUtente(Utente user) {
        ViaggioProgrammatoDAO viaggiodao = new ViaggioProgrammatoDAO();
        CorsaDAO corsadao = new CorsaDAO();
        List<ViaggioProgrammato> viaggi = viaggiodao.selectViaggioProgrammatoByUtente(user.getUsername());
        List<Corsa> corse= corsadao.selectCorseByUtente(user.getUsername());
        Map<String,CorsaViaggio> corseviaggi = new HashMap<>();
        for (ViaggioProgrammato viaggio : viaggi) {
            corseviaggi.put(viaggio.getEvento(),viaggio);
        }
        for (Corsa corsa : corse) {
            corseviaggi.put(corsa.getUtente().getUsername(),corsa);
        }
        return corseviaggi;

    }

    public void cancellaCorsa(Corsa corsa,Utente user) {
        CorsaDAO corsadao = new CorsaDAO();
        corsadao.deleteCorsa(corsa);
        caricaCorseViaggiByUtente(user);

    }

    public void cancellaViaggio(ViaggioProgrammato viaggio, Utente user) {
        ViaggioProgrammatoDAO viaggiodao = new ViaggioProgrammatoDAO();
        viaggiodao.deleteViaggio(viaggio,user);
        caricaCorseViaggiByUtente(user);

    }


}
