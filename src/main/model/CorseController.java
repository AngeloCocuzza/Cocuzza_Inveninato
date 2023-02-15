package model;

import SL_db.AutistaDAO;
import SL_db.UtenteDAO;
import SL_db.VeicoloDao;

public class CorseController {

    Utente utentecorrente;

    Autista autistacorrente;

    Veicolo veicolocorrente;

    public Utente getUtentecorrente() {
        return utentecorrente;
    }

    public Autista getAutistacorrente() {
        return autistacorrente;
    }

    public Veicolo getVeicolocorrente() {
        return veicolocorrente;
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

}
