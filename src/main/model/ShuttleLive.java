package model;

import SL_db.AutistaDAO;
import SL_db.UtenteDAO;
import ui.MainForm;

import javax.swing.*;
import java.util.Date;

public class ShuttleLive {
    public static ShuttleLive shuttlelive;

    public static ShuttleLive getInstance() {
        if(shuttlelive == null)
            shuttlelive = new ShuttleLive();
        else
            System.out.println("Istanza già creata");
        return shuttlelive;
    }

    public boolean inserisciNuovoAutista(String username, String email, String password, String nome, String cognome, String telefono, Date data_nascita) {
        Autista user = new Autista(username,email,password,nome,cognome,telefono,data_nascita);
        System.out.println(user);
        AutistaDAO daouser = new AutistaDAO();
        daouser.insertAutista(user);

        return false;

    }

}
