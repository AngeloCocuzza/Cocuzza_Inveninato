package SL_db;

import model.Autista;
import model.Utente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
                    Autista user = new Autista(rs.getString("username"), rs.getString("email"), rs.getString("password"), rs.getString("nome"), rs.getString("cognome"), rs.getString("telefono"), rs.getDate("datanascita"));
                    autista=user;
                }
                //System.out.println(autista);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return autista;
    }
    public List<Autista> selectAutistaByName(List<String> autisti) {
        List<Autista> autistiDisponib=new ArrayList<>();
        Autista autista = new Autista();
        String sql = "select * from autisti where username= ?";
        try {
            Connection conn = DBConnect.getConnection();
            if(conn!=null) {
                System.out.println("connessione con successo");
                PreparedStatement statement = conn.prepareStatement(sql);

                for(String autist: autisti) {
                    statement.setString(1, autist);
                    ResultSet rs = statement.executeQuery();
                    //System.out.println("ciao");
                    while (rs.next()) {
                        Autista user = new Autista(rs.getString("username"), rs.getString("email"), rs.getString("password"), rs.getString("nome"), rs.getString("cognome"), rs.getString("telefono"), rs.getDate("datanascita"));
                        autistiDisponib.add(user);
                    }

                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return autistiDisponib;
    }

    public List<Autista> selectSingAutistaByName(String autista) {
        List<Autista> autistibyname =new ArrayList<>();
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
                    autistibyname.add(autist);
                }

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return autistibyname;
    }


}
