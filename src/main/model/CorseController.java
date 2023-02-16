package model;

import SL_db.AutistaDAO;
import SL_db.UtenteDAO;
import SL_db.VeicoloDao;
import SL_db.ViaggioProgrammatoDAO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class CorseController {

    Utente utentecorrente;

    Autista autistacorrente;

    Veicolo veicolocorrente;

    List<ViaggioProgrammato> viaggicorrente;

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

    public List<ViaggioProgrammato> selezionaViaggioProgrammato(String evento, Date data_partenza) {
        viaggicorrente = verificaCampiViaggiProgrammato(evento, data_partenza);
        return viaggicorrente;
    }

    public List<ViaggioProgrammato> verificaCampiViaggiProgrammato(String evento, java.sql.Date data_partenza) {
        ViaggioProgrammatoDAO viaggidao = new ViaggioProgrammatoDAO();
        List<ViaggioProgrammato> viaggi = new ArrayList();
        viaggi = viaggidao.selectViaggiByEventoOrData(evento,data_partenza);


    }


}
