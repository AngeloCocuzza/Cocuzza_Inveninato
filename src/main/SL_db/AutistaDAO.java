package SL_db;

import model.*;

import java.sql.*;
import java.sql.Date;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

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
        String sql = "select * from autisti join veicolo on autisti.username=veicolo.autista join patente on autisti.username=patente.autista join disponibilita on autisti.username=disponibilita.autista where email = ? and password = ?";
        try {
            Connection conn = DBConnect.getConnection();
            if(conn!=null) {
                System.out.println("connessione con successo");
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, email);
                statement.setString(2, password);

                ResultSet rs = statement.executeQuery();
                System.out.println("ciao");
                Map<String, Veicolo> veicoli=new HashMap<>();
                Map<String, Disponibilita> disponi=new HashMap<>();
                List<Disponibilita> disp= new ArrayList<>();
                Patente patente=new Patente();
                while (rs.next()) {
                    Veicolo veicolo=new Veicolo(rs.getString("targa"),rs.getString("autista"),rs.getString("marca"),rs.getString("modello"),rs.getString("colore"),rs.getInt("n_posti") );
                    veicoli.putIfAbsent(veicolo.getTarga(),veicolo);
                    Disponibilita disponibilita =new Disponibilita(rs.getDate("giorno_disponibilita"), LocalTime.parse(rs.getString("ora_inizio")), LocalTime.parse(rs.getString("ora_fine")),rs.getString("citta_partenza"));
                    patente.setCodice(rs.getString("codice"));
                    patente.setAutista(rs.getString("autista"));
                    patente.setData_conseguimento(rs.getDate("data_conseguimento"));
                    patente.setData_scadenza(rs.getDate("data_scadenza"));
                    patente.setLivello(rs.getString("livello"));
                    disponi.putIfAbsent(disponibilita.getCitta_partenza(),disponibilita);
                    Autista user = new Autista(rs.getString("username"), rs.getString("email"), rs.getString("password"), rs.getString("nome"), rs.getString("cognome"), rs.getString("telefono"), rs.getDate("datanascita"),patente,veicoli);
                    autista=user;
                }
                autista.setDisponibilita(disponi.values().stream().collect(Collectors.toList()));

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


}
