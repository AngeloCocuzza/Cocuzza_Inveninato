package SL_db;

import model.*;

import java.awt.*;
import java.sql.*;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class ViaggioProgrammatoDAO {
    private CorseController corsacontr;
    public void insertViaggio(ViaggioProgrammato viaggio) {
        String sql = "insert into viaggi_programmati (autista,veicolo,citta_partenza,citta_destinazione,data_partenza,indirizzo_partenza,indirizzo_destinazione,ora_partenza,evento,km_corsa,prezzo,n_posti_disp) values (?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            Connection conn = DBConnect.getConnection();
            if(conn!=null) {
                System.out.println("connessione con successo");
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, viaggio.getAutista().getUsername());
                statement.setString(2, viaggio.getVeicolo().getTarga());
                statement.setString(3, viaggio.getAddress().getCitta_partenza());
                statement.setString(4, viaggio.getAddress().getCitta_destinazione());
                statement.setDate(5, (java.sql.Date)viaggio.getData_partenza());
                statement.setString(6, viaggio.getAddress().getInidirizzo_partenza());
                statement.setString(7, viaggio.getAddress().getIndirizzo_destinazione());
                statement.setTime(8, Time.valueOf(viaggio.getOra_partenza()));
                statement.setString(9, viaggio.getEvento());
                statement.setInt(10,viaggio.getAddress().getKm_corsa());
                statement.setFloat(11,viaggio.getPrezzo());
                statement.setInt(12, viaggio.getVeicolo().getN_posti());
                statement.executeUpdate();
            } else {
                System.out.println("connessione fallita");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public List<ViaggioProgrammato> selectViaggiByEventoOrData(String evento, Date data_partenza) {
        List<ViaggioProgrammato> viaggi = new ArrayList();
        String sql = "select * from viaggi_programmati where evento = ? and data_partenza = ?";
        corsacontr = CorseController.getInstance();
        try {
            Connection conn = DBConnect.getConnection();
            if(conn!=null) {
                System.out.println("connessione con successo");
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1,evento);
                statement.setDate(2, (Date) data_partenza);
                ResultSet rs = statement.executeQuery();
                //Map<String,Veicolo> veicoli = new HashMap();
                while (rs.next()) {
                    Autista auti = new Autista();
                    Veicolo veic = new Veicolo();
                    auti = corsacontr.autistaSingoloByName(rs.getString("autista"));
                    veic=corsacontr.veicoloSingoloByName(rs.getString("veicolo"));
                    //veic = auti.getVeicoli().get(rs.getString("veicolo"));
                    Address address = new Address(rs.getString("citta_partenza"),rs.getString("citta_destinazione"),rs.getString("indirizzo_partenza"),rs.getString("indirizzo_destinazione"),rs.getInt("km_corsa"));
                    ViaggioProgrammato viaggio = new ViaggioProgrammato(rs.getInt("ID"),auti, veic, rs.getDate("data_partenza"), LocalTime.parse(rs.getString("ora_partenza")),address,rs.getFloat("prezzo"),rs.getString("evento"), rs.getInt("n_posti_disp"));
                    viaggi.add(viaggio);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return viaggi;
    }

    public void insertCorsaProgrammata(Integer id, String user) {
        String sql = "insert into corsa_programmati values (?,?)";

        try {
            Connection conn = DBConnect.getConnection();
            if(conn!=null) {
                System.out.println("connessione con successo");
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, id);
                statement.setString(2, user);
                statement.executeUpdate();
            } else {
                System.out.println("connessione fallita");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ViaggioProgrammato> selectViaggioProgrammatoByUtente(String user) {
        String sql = "SELECT * FROM viaggi_programmati join corsa_programmati on viaggi_programmati.ID=corsa_programmati.ID_viaggio where corsa_programmati.utente = ?";
        List<ViaggioProgrammato> viaggi = new ArrayList<>();
        corsacontr = CorseController.getInstance();
        try {
            Connection conn = DBConnect.getConnection();
            if(conn!=null) {
                System.out.println("connessione con successo");
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1,user);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    Address address = new Address(rs.getString("citta_partenza"),rs.getString("citta_destinazione"),rs.getString("indirizzo_partenza"),rs.getString("indirizzo_destinazione"),rs.getInt("km_corsa"));
                    ViaggioProgrammato viaggio = new ViaggioProgrammato(rs.getInt("ID"),corsacontr.autistaSingoloByName(rs.getString("autista")),corsacontr.veicoloSingoloByName(rs.getString("veicolo")),rs.getDate("data_partenza"),LocalTime.parse(rs.getString("ora_partenza")),address, rs.getFloat("prezzo"), rs.getString("evento"),rs.getInt("n_posti_disp"));
                    viaggi.add(viaggio);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return viaggi;

    }

    public void updatePostiDisponibili(Integer id) {
        String sql = "UPDATE viaggi_programmati SET n_posti_disp = (n_posti_disp-1) WHERE ID = ?";

        try {
            Connection conn = DBConnect.getConnection();
            if(conn!=null) {
                System.out.println("connessione con successo");
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, id);
                statement.executeUpdate();
            } else {
                System.out.println("connessione fallita");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteViaggio(ViaggioProgrammato viaggio,Utente user) {
        String sql = "delete from corsa_programmati where ID = ? and user = ?";
        try {
            Connection conn = DBConnect.getConnection();
            if(conn!=null) {
                System.out.println("connessione con successo");
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1,viaggio.getID());
                statement.setString(2,user.getUsername());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
