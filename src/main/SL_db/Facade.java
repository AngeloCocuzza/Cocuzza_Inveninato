package SL_db;

import model.*;
import model.ShuttleLive;

import java.sql.Date;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Facade {
    public static Facade facade;

    public static Facade getInstance() {
        if(facade == null)
            facade = new Facade();
        else
            System.out.println("Istanza già creata");
        return facade;
    }
    public void salvaPatente(Patente patente) {
        try {
            PatenteDao.getInstance().insertPatente(patente);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Operazione non andata a buon fine");
        }
    }
    public Patente trovaPatenteDaAutista(String autista) {
        Patente patente=new Patente();
        try {
            patente=PatenteDao.getInstance().selectPatenteByAutista(autista);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Operazione non andata a buon fine");
        }
        return patente;
    }
    public List<Autista> caricaAutisti() {
        List<Autista> autisti=new ArrayList<>();
        try {
            autisti=AutistaDAO.getInstance().allAutisti();


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Operazione non andata a buon fine");
        }
        return autisti;


    }
    public void salvaAutista(Autista autista) {
        try {
            AutistaDAO.getInstance().insertAutista(autista);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Operazione non andata a buon fine");
        }
    }

    public void inserisciViaggioPro(ViaggioProgrammato viaggio) {
        try {
            ViaggioProgrammatoDAO.getInstance().insertViaggio(viaggio);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Operazione non andata a buon fine");
        }
    }

    public List<ViaggioProgrammato> selezioneViaggiByEventoOrData(String evento, Date data_partenza){
        List<ViaggioProgrammato> viaggi = new ArrayList<>();
        try {
            viaggi = ViaggioProgrammatoDAO.getInstance().selectViaggiByEventoOrData(evento,data_partenza);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Operazione non andata a buon fine");
        }
        return viaggi;
    }

    public void inserisciCProgrammata(Integer id, String user) {
        try {
            ViaggioProgrammatoDAO.getInstance().insertCorsaProgrammata(id,user);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Operazione non andata a buon fine");
        }
    }

    public List<ViaggioProgrammato> selezionaViaggioProgrammatoByUtente(String user) {
        List<ViaggioProgrammato> viaggi = new ArrayList<>();
        try {
            viaggi = ViaggioProgrammatoDAO.getInstance().selectViaggioProgrammatoByUtente(user);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Operazione non andata a buon fine");
        }
        return viaggi;
    }

    public List<ViaggioProgrammato> selezionaViaggioProgrammatoByAutista(String autista) {
        List<ViaggioProgrammato> viaggi = new ArrayList<>();
        try {
            viaggi = ViaggioProgrammatoDAO.getInstance().selectViaggioProgrammatoByAutista(autista);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Operazione non andata a buon fine");
        }
        return viaggi;
    }

    public void aggiornaPostiDisponibili(Integer id) {
        try {
            ViaggioProgrammatoDAO.getInstance().updatePostiDisponibili(id);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Operazione non andata a buon fine");
        }
    }

    public void eliminaViaggio(ViaggioProgrammato viaggio,Utente user) {
        try {
            ViaggioProgrammatoDAO.getInstance().deleteViaggio(viaggio,user);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Operazione non andata a buon fine");
        }
    }

    /////veicolo/////

    public void inserisciVeicolo(Veicolo veicol) {
        try {
            VeicoloDao.getInstance().insertVeicolo(veicol);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Operazione non andata a buon fine");
        }
    }

    public Map<String,Veicolo> tuttiVeicoloAutista(String autista) {
        Map<String, Veicolo> veicoli = new HashMap<>();
        try {
            veicoli=VeicoloDao.getInstance().allVeicoloAutista(autista);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Operazione non andata a buon fine");
        }
        return veicoli;
    }

    public Map<String,Veicolo> tuttiVeicoloAutista() {
        Map<String, Veicolo> veicoli = new HashMap<>();
        try {
            veicoli=VeicoloDao.getInstance().allVeicolo();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Operazione non andata a buon fine");
        }
        return veicoli;
    }
    public Veicolo tuttiVeicoloTarga(String targa) {
        Veicolo veicolitarga = new Veicolo();
        try {
            veicolitarga=VeicoloDao.getInstance().allVeicoloTarga(targa);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Operazione non andata a buon fine");
        }
        return veicolitarga;
    }

    ////recensione viaggio/////

    public void insRecensione(CorsaViaggio viaggio) {
        try {
            RecensioneViaggioDAO.getInstance().insertRecensione(viaggio);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Operazione non andata a buon fine");
        }
    }

    public List<Recensione> tuttiRecensioniAutista(String autista) {
        List<Recensione> listaRecen = new ArrayList<>();
        try {
            listaRecen=RecensioneViaggioDAO.getInstance().allRecensioniAutista(autista);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Operazione non andata a buon fine");
        }
        return listaRecen;
    }

    public Recensione recenViaggio(ViaggioProgrammato viaggio) {
        Recensione listaRecen = new Recensione();
        try {
            listaRecen=RecensioneViaggioDAO.getInstance().recensioneViaggio(viaggio);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Operazione non andata a buon fine");
        }
        return listaRecen;
    }

    ////recensione corsa/////

    public void insRecensioneC(CorsaViaggio viaggio) {
        try {
            RecensioneCorsaDAO.getInstance().insertRecensione(viaggio);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Operazione non andata a buon fine");
        }
    }

    public List<Recensione> tuttiRecensioniAutistaC(String autista) {
        List<Recensione> listaRecen = new ArrayList<>();
        try {
            listaRecen=RecensioneCorsaDAO.getInstance().allRecensioniAutista(autista);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Operazione non andata a buon fine");
        }
        return listaRecen;
    }

    public Recensione recenCorsa(Corsa viaggio) {
        Recensione listaRecen = new Recensione();
        try {
            listaRecen=RecensioneCorsaDAO.getInstance().recensioneCorsa(viaggio);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Operazione non andata a buon fine");
        }
        return listaRecen;
    }

    ////CORSE/////
    public void insCorsa(Corsa corsa) {
        try {
            CorsaDAO.getInstance().insertCorsa(corsa);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Operazione non andata a buon fine");
        }
    }

    public List<Corsa> selezionaCorseByUtente(String utente) {
        List<Corsa> allcorsa = new ArrayList<>();
        try {
            allcorsa=CorsaDAO.getInstance().selectCorseByUtente(utente);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Operazione non andata a buon fine");
        }
        System.out.println(allcorsa);
        return allcorsa;
    }

    public List<Corsa> selezionaCorseByAutista(String autista) {
        List<Corsa> allcorsa = new ArrayList<>();
        try {
            allcorsa=CorsaDAO.getInstance().selectCorseByAutista(autista);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Operazione non andata a buon fine");
        }
        return allcorsa;
    }

    public void cancCorsa(Corsa corsa) {
        try {
            CorsaDAO.getInstance().deleteCorsa(corsa);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Operazione non andata a buon fine");
        }
    }

    public Autista trovaAutistaDalNome(String autista) {
        Autista autist=new Autista();
        try {
            autist=AutistaDAO.getInstance().selectAutistaSingoloByName(autista);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Operazione non andata a buon fine");
        }
        return autist;
    }
    public Autista trovaAutista(String email,String password) {
        Autista autist=new Autista();
        try {
            autist=AutistaDAO.getInstance().selectAutista(email,password);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Operazione non andata a buon fine");
        }
        return autist;
    }


    public void salvaUtente(Utente user) {
        try {
            UtenteDAO.getInstance().insertUtente(user);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Operazione non andata a buon fine");
        }
    }

    public List<Utente> caricaUtenti() {
        List<Utente> utenti=new ArrayList<>();
        try {
            utenti=UtenteDAO.getInstance().allUtente();


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Operazione non andata a buon fine");
        }
        return utenti;

    }

    public Utente trovaUtente(String email, String password) {
        Utente utente=new Utente();
        try {
            utente=UtenteDAO.getInstance().selectUtente(email,password);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Operazione non andata a buon fine");
        }
        return utente;
    }

    public Utente trovaUtenteDalNome(String utente) {
        Utente user=new Utente();
        try {
            user=UtenteDAO.getInstance().selectUtenteSingoloByName(utente);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Operazione non andata a buon fine");
        }
        return user;
    }

    public List<Disponibilita> caricaDisponibilita() {
        List<Disponibilita> disp=new ArrayList<>();
        try {
            disp=DisponibilitaDAO.getInstance().allDisponibilita();


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Operazione non andata a buon fine");
        }
        return disp;
    }

    public void salvaDisponibilita(Disponibilita dispo, Autista autista) {
        try {
            DisponibilitaDAO.getInstance().insertDisponibilita(dispo,autista);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Operazione non andata a buon fine");
        }
    }

    public List<Autista> caricaAutistiDisponibili(String partenza, Date data_partenza, LocalTime ora) {
        List<Autista> autisti=new ArrayList<>();
        try {
            autisti=DisponibilitaDAO.getInstance().selectAutistiDisponibili(partenza,data_partenza,ora);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Operazione non andata a buon fine");
        }
        return autisti;
    }

    public List<Disponibilita> caricaDisponibilitaAutista(String autista) {
        List<Disponibilita> disp=new ArrayList<>();
        try {
            disp=DisponibilitaDAO.getInstance().allDisponibilitaByAutista(autista);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Operazione non andata a buon fine");
        }
        return disp;
    }
}
