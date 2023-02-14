package SL_db;

import model.Autista;
import model.Corsa;
import model.Veicolo;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class CorsaDAO {
    public void insertCorsa(Corsa corsa) {
        //Utente user = new Utente();
        String sql = "insert into corsa values (?,?,?,?,?,?,?,?,?,?,?)";

        try {
            Connection conn = DBConnect.getConnection();
            if(conn!=null) {
                System.out.println("connessione con successo");
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, corsa.getAutista().getUsername());
                statement.setString(2, corsa.getUtente().getEmail());
                statement.setString(3, corsa.getVeicolo().getTarga());
                statement.setString(4, corsa.getCitta_partenza());
                statement.setString(5, corsa.getCitta_destinazione());
                statement.setDate(6, (Date) corsa.getData_partenza());
                statement.setString(7, corsa.getInidirizzo_partenza());
                statement.setString(8, corsa.getIndirizzo_destinazione());
                statement.setTime(9, Time.valueOf(corsa.getOra_partenza()));
                statement.setInt(10, corsa.getKm_corsa());
                statement.setFloat(11,corsa.getPrezzo());
                statement.executeUpdate();
            } else {
                System.out.println("connessione fallita");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Corsa> allCorsa() {
        List<Corsa> allcorsa = new ArrayList<>();
        String sql = "select * from corsa";

        try {
            Connection conn = DBConnect.getConnection();
            if(conn!=null) {
                System.out.println("connessione con successo");
                PreparedStatement statement = conn.prepareStatement(sql);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    UtenteDAO uDAO = new UtenteDAO();
                    AutistaDAO aDAO = new AutistaDAO();
                    VeicoloDao vDAO = new VeicoloDao();
                    Corsa corsa = new Corsa(aDAO.selectSingAutistaByName(rs.getString("autista")).get(1),uDAO.selectUtentiByName(rs.getString("utente")).get(0),vDAO.allVeicoloAutista(rs.getString("veicolo")).get(0), rs.getString("citta_partenza"),rs.getString("citta_destinazione"),rs.getDate("data_partenza"),rs.getString("indirizzo_partenza"),rs.getString("indirizzo_destinazione"), LocalTime.parse(rs.getString("ora_partenza")),rs.getInt("km_corsa"),rs.getFloat("prezzo"));
                    allcorsa.add(corsa);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allcorsa;
    }
}
