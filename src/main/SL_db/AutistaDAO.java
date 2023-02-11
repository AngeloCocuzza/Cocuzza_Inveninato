package SL_db;

import model.Autista;
import model.Utente;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AutistaDAO {
    public void insertAutista(Autista user) {
        //Utente user = new Utente();
        String sql = "insert into autisti values (?,?,?,?,?,?,?)";

        try {
            Connection conn = DBConnect.getConnection();
            if(conn!=null) {
                System.out.println("connessione con successo");
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, user.getUsername());
                statement.setString(2, user.getEmail());
                statement.setString(3, user.getPassword());
                statement.setString(4, user.getNome());
                statement.setString(5, user.getCognome());
                statement.setString(6, user.getTelefono());
                statement.setDate(7, (Date) user.getData_nascita());
                statement.executeUpdate();
            } else {
                System.out.println("connessione fallita");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
