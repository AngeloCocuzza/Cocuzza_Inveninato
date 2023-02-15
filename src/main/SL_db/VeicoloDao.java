package SL_db;

import model.Autista;
import model.Utente;
import model.Veicolo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeicoloDao {
    public void insertVeicolo(Veicolo veicol) {
        //Utente user = new Utente();
        String sql = "insert into veicolo values (?,?,?,?,?,?)";

        try {
            Connection conn = DBConnect.getConnection();
            if(conn!=null) {
                System.out.println("connessione con successo");
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, veicol.getTarga());
                statement.setString(2, veicol.getAutista());
                statement.setString(3, veicol.getMarca());
                statement.setString(4, veicol.getModello());
                statement.setString(5, veicol.getColore());
                statement.setInt(6, veicol.getN_posti());

                statement.executeUpdate();
            } else {
                System.out.println("connessione fallita");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Veicolo> allVeicolo() {
        List<Veicolo> allveicolo = new ArrayList<Veicolo>();
        String sql = "select * from veicolo";

        try {
            Connection conn = DBConnect.getConnection();
            if(conn!=null) {
                System.out.println("connessione con successo");
                PreparedStatement statement = conn.prepareStatement(sql);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    Veicolo veicolo = new Veicolo(rs.getString("targa"), rs.getString("autista"), rs.getString("marca"), rs.getString("modello"),rs.getString("colore"), rs.getInt("n_posti"));
                    allveicolo.add(veicolo);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allveicolo;
    }

    public List<Veicolo> allVeicoloAutista(String autista) {
        List<Veicolo> veicoliauti = new ArrayList<Veicolo>();
        String sql = "select * from veicolo where autista = ?";

        try {
            Connection conn = DBConnect.getConnection();
            if(conn!=null) {
                System.out.println("connessione con successo");
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1,autista);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    Veicolo veicolo = new Veicolo(rs.getString("targa"), rs.getString("autista"), rs.getString("marca"), rs.getString("modello"),rs.getString("colore"), rs.getInt("n_posti"));
                    veicoliauti.add(veicolo);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return veicoliauti;
    }

    public Veicolo allvVeicoloTarga(String targa) {
        Veicolo veicolitarga = new Veicolo();
        String sql = "select * from veicolo where targa = ?";

        try {
            Connection conn = DBConnect.getConnection();
            if(conn!=null) {
                System.out.println("connessione con successo");
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1,targa);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    Veicolo veicolo = new Veicolo(rs.getString("targa"), rs.getString("autista"), rs.getString("marca"), rs.getString("modello"),rs.getString("colore"), rs.getInt("n_posti"));
                    veicolitarga=veicolo;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return veicolitarga;
    }
}
