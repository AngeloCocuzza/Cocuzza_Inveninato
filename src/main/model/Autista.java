package model;
import model.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Autista extends Utente{
    private Patente patente;
    private List<Veicolo> veicoli;

    public Autista(String username, String email, String password, String nome, String cognome, String telefono, Date data_nascita, Patente patente, List<Veicolo> veicoli) {
        super(username, email, password, nome, cognome, telefono, data_nascita);
        this.patente = patente;
        this.veicoli = veicoli;
    }

    public List<Veicolo> getVeicoli() {
        return veicoli;
    }

    public void setVeicoli(List<Veicolo> veicoli) {
        this.veicoli = veicoli;
    }

    public Patente getPatente() {
        return patente;
    }

    public void setPatente(Patente patente) {
        this.patente = patente;
    }

    public Autista(String username, String email, String password, String nome, String cognome, String telefono, Date data_nascita) {
        super(username, email, password, nome, cognome, telefono, data_nascita);
    }

    public Autista() {

    }

    public Object[] toArray() {
        Object[] a = new Object[] {
                getUsername(),
                getEmail(),
                getNome(),
                getCognome(),
                getTelefono()
        };
        return a;
    }

    @Override
    public String toString() {
        return "Autista{" +
                "patente=" + patente +
                ", veicoli=" + veicoli +
                '}';
    }
}
