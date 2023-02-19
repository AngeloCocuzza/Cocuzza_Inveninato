package SL_db;

import model.*;

import java.sql.*;
import java.sql.Date;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class AutistaDAO {

    public static AutistaDAO autistadao;

    public static AutistaDAO getInstance() {
        if (autistadao == null)
            autistadao = new AutistaDAO();
        else
            System.out.println("Istanza già creata");
        return autistadao;
    }
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
    public List<Autista> allAutisti() {
        List<Autista> allAutisti = new ArrayList<Autista>();
        String sql = "select * from autisti";

        try {
            Connection conn = DBConnect.getConnection();
            if(conn!=null) {
                System.out.println("connessione con successo");
                PreparedStatement statement = conn.prepareStatement(sql);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    Autista autista = new Autista(rs.getString("username"), rs.getString("email"), rs.getString("password"), rs.getString("nome"), rs.getString("cognome"), rs.getString("telefono"), rs.getDate("datanascita"));
                    allAutisti.add(autista);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allAutisti;
    }





    public Autista selectAutistaSingoloByName(String autista) {
        Autista autistibyname =new Autista();
        String sql = "select * from autisti where username= ?";
        try {
            Connection conn = DBConnect.getConnection();
            if(conn!=null) {
                System.out.println("connessione con successo");
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, autista);
                ResultSet rs = statement.executeQuery();
                //System.out.println("ciao");
                while (rs.next()) {
                    Autista autist = new Autista(rs.getString("username"), rs.getString("email"), rs.getString("password"), rs.getString("nome"), rs.getString("cognome"), rs.getString("telefono"), rs.getDate("datanascita"));
                    autistibyname=autist;
                }

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return autistibyname;
    }

    public Autista selectAutista(String email, String password) {
        Autista autistilogin =new Autista();
        String sql = "select * from autisti where email= ? and password = ?";
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
                    Autista autist = new Autista(rs.getString("username"), rs.getString("email"), rs.getString("password"), rs.getString("nome"), rs.getString("cognome"), rs.getString("telefono"), rs.getDate("datanascita"));
                    autistilogin=autist;
                }

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return autistilogin;
    }


}
