package SL_db;

import model.*;
import model.ShuttleLive;

import java.sql.Date;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
