package model;

import SL_db.*;
import ui.GestisciPrenotazioni;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static SL_db.Facade.facade;

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
        autistacorrente= Facade.getInstance().trovaAutistaDalNome(autista);
        return autistacorrente;
    }

    public Utente utenteSingoloByName(String utente) {
        utentecorrente= Facade.getInstance().trovaUtenteDalNome(utente);
        return utentecorrente;
    }

    public Veicolo veicoloSingoloByName(String veicolo) {
        //VeicoloDao veicdao = new VeicoloDao();
        veicolocorrente= Facade.getInstance().tuttiVeicoloTarga(veicolo);
        //veicdao.allvVeicoloTarga(veicolo);
        return veicolocorrente;
    }

    public List<ViaggioProgrammato> selezionaViaggioProgrammato(String evento, Date data_partenza) throws Exception {
        viaggicorrente = verificaCampiViaggiProgrammato(evento, data_partenza);
        return viaggicorrente;
    }

    public void inserisciCorsaProgrammata(ViaggioProgrammato viaggio,Utente user) {
        //ViaggioProgrammatoDAO viaggiodao = new ViaggioProgrammatoDAO();
        List<Utente> utenti = viaggio.getUtentiPrenotati();
        utenti.add(user);
        viaggio.setUtentiPrenotati(utenti);
        System.out.println("Lista" + utenti);
        Facade.getInstance().inserisciCProgrammata(viaggio.getID(),user.getUsername());
        //viaggiodao.insertCorsaProgrammata(viaggio.getID(),user.getUsername());
    }
    public void inserisciRecensione(CorsaViaggio viaggio) throws Exception {
        //Recensione vuota = new Recensione(null,"");
        if (viaggio instanceof Corsa) {
            //RecensioneCorsaDAO recensionedao = new RecensioneCorsaDAO();
            if (Facade.getInstance().recenCorsa((Corsa) viaggio) != null){
                System.out.println("sono qui");
                throw new Exception("recensione già inserita");
            } else if(Facade.getInstance().recenCorsa((Corsa) viaggio) == null) {
                Facade.getInstance().insRecensioneC(viaggio);
            }
        } else if(viaggio instanceof ViaggioProgrammato){
            //RecensioneViaggioDAO recensionedao = new RecensioneViaggioDAO();
            if(Facade.getInstance().recenViaggio((ViaggioProgrammato) viaggio) != null) {
               throw new Exception("recensione già inserita");
            } else if(Facade.getInstance().recenViaggio((ViaggioProgrammato) viaggio) == null){
                Facade.getInstance().insRecensione(viaggio);
                //recensionedao.insertRecensione(viaggio);
            }
        }

    }
    public List<Recensione> selezionaRecensioniAutista(String autista) {
        List<Recensione> recensioni=new ArrayList<>();

        //RecensioneCorsaDAO recensionecorsadao = new RecensioneCorsaDAO();
        recensioni=Facade.getInstance().tuttiRecensioniAutistaC(autista);
        //recensionecorsadao.allRecensioniAutista(autista);

       // RecensioneViaggioDAO recensioneviaggiodao = new RecensioneViaggioDAO();
        recensioni.addAll(Facade.getInstance().tuttiRecensioniAutista(autista));
                //recensioneviaggiodao.allRecensioniAutista(autista));
        return recensioni;

    }


    public List<ViaggioProgrammato> verificaCampiViaggiProgrammato(String evento, java.sql.Date data_partenza) throws Exception {
        //ViaggioProgrammatoDAO viaggidao = new ViaggioProgrammatoDAO();
        List<ViaggioProgrammato> viaggi = new ArrayList();

        if(evento.equals("") || data_partenza==null) {
            throw new Exception("riempire tutti i campi");
        }
        if(data_partenza.before(new java.util.Date())) {
            throw new Exception("data non valida");
        }
        viaggi = Facade.getInstance().selezioneViaggiByEventoOrData(evento,data_partenza);
        return viaggi;

    }

    public void diminuisciPostiDisponibili(ViaggioProgrammato viaggio){
        //ViaggioProgrammatoDAO viaggiodao = new ViaggioProgrammatoDAO();
        viaggio.setPostiDisponibili(viaggio.getVeicolo().getN_posti()-1);
        Facade.getInstance().aggiornaPostiDisponibili(viaggio.getID());
        //viaggiodao.updatePostiDisponibili(viaggio.getID());
    }

    public List<CorsaViaggio> caricaCorseViaggiByUtente(Utente user) {
        //ViaggioProgrammatoDAO viaggiodao = new ViaggioProgrammatoDAO();
        //CorsaDAO corsadao = new CorsaDAO();
        System.out.println(user.getUsername());
        List<ViaggioProgrammato> viaggi = Facade.getInstance().selezionaViaggioProgrammatoByUtente(user.getUsername());
        //viaggiodao.selectViaggioProgrammatoByUtente(user.getUsername());
        List<Corsa> corse= Facade.getInstance().selezionaCorseByUtente(user.getUsername());
        //corsadao.selectCorseByUtente(user.getUsername());
        List<CorsaViaggio> corseviaggi = new ArrayList<>();
        corseviaggi.addAll(corse);
        corseviaggi.addAll(viaggi);
        return corseviaggi;
    }

    public List<CorsaViaggio> caricaCorseViaggiByAutista(Autista autista) {
        //ViaggioProgrammatoDAO viaggiodao = new ViaggioProgrammatoDAO();
        //CorsaDAO corsadao = new CorsaDAO();
        List<ViaggioProgrammato> viaggi = Facade.getInstance().selezionaViaggioProgrammatoByAutista(autista.getUsername());
        //viaggiodao.selectViaggioProgrammatoByAutista(autista.getUsername());
        List<Corsa> corse= Facade.getInstance().selezionaCorseByAutista(autista.getUsername());
                //corsadao.selectCorseByAutista(autista.getUsername());
        List<CorsaViaggio> corseviaggi = new ArrayList<>();
        corseviaggi.addAll(corse);
        corseviaggi.addAll(viaggi);
        return corseviaggi;
    }

    public void cancellaCorsa(Corsa corsa,Utente user) {
        //CorsaDAO corsadao = new CorsaDAO();
        //corsadao.deleteCorsa(corsa);
        Facade.getInstance().cancCorsa(corsa);
        caricaCorseViaggiByUtente(user);
    }

    public void cancellaViaggio(ViaggioProgrammato viaggio, Utente user) {
        //ViaggioProgrammatoDAO viaggiodao = new ViaggioProgrammatoDAO();
        //viaggiodao.deleteViaggio(viaggio,user);
        Facade.getInstance().eliminaViaggio(viaggio,user);
        caricaCorseViaggiByUtente(user);

    }


}
