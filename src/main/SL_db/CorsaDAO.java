package SL_db;

import model.*;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class CorsaDAO {

    private CorseController corsacontr;
    private ShuttleLive shuttlelive;
    public void insertCorsa(Corsa corsa) {


        String sql = "insert into corsa (autista,utente,veicolo,citta_partenza,citta_destinazione,data_partenza,indirizzo_partenza,indirizzo_destinazione,ora_partenza,km_corsa,prezzo) values (?,?,?,?,?,?,?,?,?,?,?)";

        try {
            Connection conn = DBConnect.getConnection();
            if(conn!=null) {
                System.out.println("connessione con successo");
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, corsa.getAutista().getUsername());
                statement.setString(2, corsa.getUtente().getUsername());
                statement.setString(3, corsa.getVeicolo().getTarga());
                statement.setString(4, corsa.getAddress().getCitta_partenza());
                statement.setString(5, corsa.getAddress().getCitta_destinazione());
                statement.setDate(6, (Date) corsa.getData_partenza());
                statement.setString(7, corsa.getAddress().getInidirizzo_partenza());
                statement.setString(8, corsa.getAddress().getIndirizzo_destinazione());
                statement.setTime(9, Time.valueOf(corsa.getOra_partenza()));
                statement.setInt(10, corsa.getAddress().getKm_corsa());
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
        corsacontr = CorseController.getInstance();
        List<Corsa> allcorsa = new ArrayList<>();
        String sql = "select * from corsa";

        try {
            Connection conn = DBConnect.getConnection();
            if(conn!=null) {
                System.out.println("connessione con successo");
                PreparedStatement statement = conn.prepareStatement(sql);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    Utente user = new Utente();
                    Autista auti = new Autista();
                    Veicolo veic = new Veicolo();
                    auti = corsacontr.autistaSingoloByName(rs.getString("autista"));
                    user = corsacontr.utenteSingoloByName(rs.getString("utente"));
                    //veic = corsacontr.veicoloSingoloByName(rs.getString("veicolo"));
                    veic = auti.getVeicoli().get(rs.getString("veicolo"));
                    Address address = new Address(rs.getString("citta_partenza"),rs.getString("citta_destinazione"),rs.getString("indirizzo_partenza"),rs.getString("indirizzo_destinazione"),rs.getInt("km_corsa"));
                    Corsa corsa = new Corsa(auti, veic, rs.getDate("data_partenza"), LocalTime.parse(rs.getString("ora_partenza")), address, rs.getFloat("prezzo"), user);
                    allcorsa.add(corsa);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allcorsa;
    }

    public List<Corsa> selectCorseByUtente(String utente) {
        corsacontr = CorseController.getInstance();
        List<Corsa> allcorsa = new ArrayList<>();
        String sql = "select * from corsa where utente = ?";

        try {
            Connection conn = DBConnect.getConnection();
            if(conn!=null) {
                System.out.println("connessione con successo");
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1,utente);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    Utente user = new Utente();
                    Autista auti = new Autista();
                    Veicolo veic = new Veicolo();
                    auti = corsacontr.autistaSingoloByName(rs.getString("autista"));
                    user = corsacontr.utenteSingoloByName(rs.getString("utente"));
                    //veic = corsacontr.veicoloSingoloByName(rs.getString("veicolo"));
                    veic = corsacontr.veicoloSingoloByName("targa");
                    Address address = new Address(rs.getString("citta_partenza"),rs.getString("citta_destinazione"),rs.getString("indirizzo_partenza"),rs.getString("indirizzo_destinazione"),rs.getInt("km_corsa"));
                    Corsa corsa = new Corsa(rs.getInt("ID"),auti, veic, rs.getDate("data_partenza"), LocalTime.parse(rs.getString("ora_partenza")), address, rs.getFloat("prezzo"), user);
                    allcorsa.add(corsa);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allcorsa;
    }

    public void deleteCorsa(Corsa corsa) {
        String sql = "delete from corsa where ID = ?";
        try {
            Connection conn = DBConnect.getConnection();
            if(conn!=null) {
                System.out.println("connessione con successo");
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1,corsa.getID());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
