package model;
import model.*;

import java.util.Date;
import java.util.Objects;

public class Autista extends Utente{
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
                "username='" + getUsername() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", nome='" + getNome() + '\'' +
                ", cognome='" + getCognome() + '\'' +
                ", telefono=" + getTelefono() +
                ", data_nascita=" + getData_nascita() +
                '}';
    }
}
