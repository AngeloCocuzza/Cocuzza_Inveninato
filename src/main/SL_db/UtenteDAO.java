package SL_db;
import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtenteDAO {
    public void insertUtente(Utente user) {

        String sql = "insert into utenti values (?,?,?,?,?,?,?)";

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
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Utente> allUtente() {
        List<Utente> allUsers = new ArrayList<Utente>();
        String sql = "select * from utenti";

        try {
            Connection conn = DBConnect.getConnection();
            if(conn!=null) {
                System.out.println("connessione con successo");
                PreparedStatement statement = conn.prepareStatement(sql);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    Utente user = new Utente(rs.getString("username"), rs.getString("email"), rs.getString("password"), rs.getString("nome"), rs.getString("cognome"), rs.getString("telefono"), rs.getDate("datanascita"));
                    allUsers.add(user);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allUsers;
    }
    public Utente selectUtente(String email, String password) {
        Utente utente = new Utente();
        String sql = "select * from utenti where email = ? and password = ?";
        try {
            Connection conn = DBConnect.getConnection();
            if(conn!=null) {
                System.out.println("connessione con successo");
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, email);
                statement.setString(2, password);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    Utente user = new Utente(rs.getString("username"), rs.getString("email"), rs.getString("password"), rs.getString("nome"), rs.getString("cognome"), rs.getString("telefono"), rs.getDate("datanascita"));
                    utente=user;
                }

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return utente;
    }

}
