package model;

import SL_db.*;
import ui.GestisciPrenotazioni;

import java.sql.Date;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static SL_db.Facade.facade;

public class CorseController {

    Utente utentecorrente;

    Corsa corsaCorrente;

    ViaggioProgrammato viaggioCorrente;

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
        viaggio.setUtente(user);
        Facade.getInstance().inserisciCProgrammata(viaggio.getID(),user.getUsername());
    }
    public void inserisciRecensione(CorsaViaggio viaggio,Integer voto, String commento) throws Exception {
        //Recensione vuota = new Recensione(null,"");
        if(commento.equals("") || voto==null) {
            throw new Exception("riempire tutti i campi");
        }
        if(voto>5 || voto<0) {
            throw new Exception("il voto deve essere compreso tra 1 e 5");
        }
        Recensione review = new Recensione(voto,commento);
        if (viaggio instanceof Corsa) {
            //RecensioneCorsaDAO recensionedao = new RecensioneCorsaDAO();
            if (Facade.getInstance().recenCorsa((Corsa) viaggio) != null){
                System.out.println("sono qui");
                throw new Exception("recensione già inserita");
            } else if(Facade.getInstance().recenCorsa((Corsa) viaggio) == null) {
                viaggio.setRecensione(review);
                Facade.getInstance().insRecensioneC(viaggio);

            }
        } else if(viaggio instanceof ViaggioProgrammato){
            //RecensioneViaggioDAO recensionedao = new RecensioneViaggioDAO();
            if(Facade.getInstance().recenViaggio((ViaggioProgrammato) viaggio) != null) {
               throw new Exception("recensione già inserita");
            } else if(Facade.getInstance().recenViaggio((ViaggioProgrammato) viaggio) == null){
                viaggio.setRecensione(review);
                Facade.getInstance().insRecensione(viaggio);

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
        viaggio.setPostiDisponibili(viaggio.getVeicolo().getN_posti()-1);
        Facade.getInstance().aggiornaDimPostiDisponibili(viaggio.getID());
    }

    public void aumentaPostiDisponibili(ViaggioProgrammato viaggio){
        viaggio.setPostiDisponibili(viaggio.getVeicolo().getN_posti()+1);
        Facade.getInstance().aggiornaAumPostiDisponibili(viaggio.getID());
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

    public Corsa creaCorsa(Veicolo veicolo, Autista autista, Utente utente, String c_partenza, String c_arrivo, java.util.Date data_partenza, LocalTime ora_partenza, String indirizzopart, String indirizzodest) {

        Address address = new Address(c_partenza,c_arrivo,indirizzodest,indirizzopart,getKm());
        Corsa corsa = new Corsa(autista,veicolo, data_partenza, ora_partenza, address, utente);
        corsa.setPrezzo(corsa.getFee());
        return corsa;
    }

    public Integer getKm() {
        int km = (int) Math.floor(Math.random() * (100) + 1);
        return km;
    }

    public Corsa inserisciCorsa(Corsa corsa) throws Exception {
        corsaCorrente=verificaCampiCorsa(corsa);
        return corsaCorrente;
    }
    public ViaggioProgrammato inserisciViaggio(Autista autista, String targa, String evento, Float prezzo, LocalTime ora_partenza, java.util.Date data_partenza, String citta_partenza, String citta_arrivo, String ind_partenza, String ind_arrivo, Integer km_corsa) throws Exception {
        Address address = new Address(citta_partenza,citta_arrivo,ind_partenza,ind_arrivo,km_corsa);
        Veicolo veicolo = autista.getVeicoli().get(targa);
        ViaggioProgrammato viaggio = new ViaggioProgrammato(autista,veicolo,data_partenza,ora_partenza,address,prezzo,evento);
        viaggio.setPostiDisponibili(veicolo.getN_posti());
        viaggioCorrente = verificaCampiViaggio(viaggio);
        return viaggioCorrente;
    }

    public ViaggioProgrammato verificaCampiViaggio(ViaggioProgrammato corsa) throws Exception {
        if (corsa.getEvento()==("")||corsa.getAddress().getCitta_destinazione().equals("") || corsa.getAddress().getCitta_partenza().equals("") || corsa.getAddress().getIndirizzo_destinazione().equals("") || corsa.getAddress().getInidirizzo_partenza().equals("") || (corsa.getData_partenza() == null) || (corsa.getOra_partenza() == null) || (corsa.getAddress().getKm_corsa() == null)) {
            throw new Exception("riempire tutti i campi");
        }
        //ViaggioProgrammatoDAO viaggiodao=new ViaggioProgrammatoDAO();
        Facade.getInstance().inserisciViaggioPro(corsa);
        return corsa;
    }



    public Corsa verificaCampiCorsa(Corsa corsa) throws Exception {
        if (corsa.getAddress().getCitta_destinazione().equals("") || corsa.getAddress().getCitta_partenza().equals("") || corsa.getAddress().getIndirizzo_destinazione().equals("") || corsa.getAddress().getInidirizzo_partenza().equals("") || corsa.getData_partenza() == null || corsa.getOra_partenza() == null) {
            throw new Exception("riempire tutti i campi");
        }
        Facade.getInstance().insCorsa(corsa);;
        return corsa;
    }


}
