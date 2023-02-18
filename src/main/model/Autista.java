package model;
import model.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Autista extends Utente{
    private Patente patente;

    private Map<String,Veicolo> veicoli;
    private List<Disponibilita> disponibilita;


    public Autista(String username, String email, String password, String nome, String cognome, String telefono, Date data_nascita, Patente patente, Map<String,Veicolo> veicoli) {
        super(username, email, password, nome, cognome, telefono, data_nascita);
        this.patente = patente;
        this.veicoli = veicoli;
    }

    public Autista(Patente patente, Map<String, Veicolo> veicoli, List<Disponibilita> disponibilita) {
        this.patente = patente;
        this.veicoli = veicoli;
        this.disponibilita = disponibilita;
    }

    public Autista(String username, String email, String password, String nome, String cognome, String telefono, Date data_nascita, Patente patente) {
        super(username, email, password, nome, cognome, telefono, data_nascita);
        this.patente = patente;
    }


    public List<Disponibilita> getDisponibilita() {
        return disponibilita;
    }

    public void setDisponibilita(List<Disponibilita> disponibilita) {
        this.disponibilita = disponibilita;
    }

    public Map<String, Veicolo> getVeicoli() {
        return veicoli;
    }

    public void setVeicoli(Map<String, Veicolo> veicoli) {
        this.veicoli = veicoli;
    }

    public Patente getPatente() {
        return patente;
    }

    public void setPatente(Patente patente) {
        this.patente = patente;
    }

    public Autista(String username, String email, String password, String nome, String cognome, String telefono, Date data_nascita, Patente patente, Map<String, Veicolo> veicoli, List<Disponibilita> disponibilita) {
        super(username, email, password, nome, cognome, telefono, data_nascita);
        this.patente = patente;
        this.veicoli = veicoli;
        this.disponibilita = disponibilita;
    }

    public Autista(String username, String email, String password, String nome, String cognome, String telefono, Date data_nascita) {
        super(username, email, password, nome, cognome, telefono, data_nascita);
    }

    public Autista() {

    }

    @Override
    public String toString() {
        return "Autista{" +
                "patente=" + patente +
                ", veicoli=" + veicoli +
                ", disponibilita=" + disponibilita +
                '}';
    }
}
