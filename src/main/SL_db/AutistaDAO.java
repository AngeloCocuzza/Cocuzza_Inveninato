package SL_db;

import model.Autista;
import model.Utente;

import java.sql.*;

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


    public Autista selectAutista(String email, String password) {
        Autista autista = new Autista();
        String sql = "select * from autisti where email = ? and password = ?";
        try {
            Connection conn = DBConnect.getConnection();
            if(conn!=null) {
                System.out.println("connessione con successo");
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, email);
                statement.setString(2, password);

                ResultSet rs = statement.executeQuery();
                //System.out.println("ciao");
                while (rs.next()) {
                    Autista user = new Autista(rs.getString("nomeutente"), rs.getString("email"), rs.getString("password"), rs.getString("name"), rs.getString("cognome"), rs.getString("telefono"), rs.getDate("datanascita"));
                    autista=user;
                }
                //System.out.println(autista);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return autista;
    }
}
