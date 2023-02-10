package model;
import model.*;

import java.util.Date;

public class Autista extends Utente{
    public Autista(String username, String email, String password, String nome, String cognome, Integer telefono, Date data_nascita) {
        super(username, email, password, nome, cognome, telefono, data_nascita);
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
