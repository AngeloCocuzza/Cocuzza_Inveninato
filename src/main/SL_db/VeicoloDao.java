package SL_db;

import model.Autista;
import model.Utente;
import model.Veicolo;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VeicoloDao {

    public static VeicoloDao veicolodao;

    public static VeicoloDao getInstance() {
        if(veicolodao == null)
            veicolodao = new VeicoloDao();
        else
            System.out.println("Istanza già creata");
        return veicolodao;
    }
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

    public Map<String,Veicolo> allVeicolo() {
        Map<String,Veicolo> allveicolo = new HashMap<>();
        String sql = "select * from veicolo";

        try {
            Connection conn = DBConnect.getConnection();
            if(conn!=null) {
                System.out.println("connessione con successo");
                PreparedStatement statement = conn.prepareStatement(sql);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    Veicolo veicolo = new Veicolo(rs.getString("targa"), rs.getString("autista"), rs.getString("marca"), rs.getString("modello"),rs.getString("colore"), rs.getInt("n_posti"));
                    allveicolo.putIfAbsent(veicolo.getTarga(), veicolo);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allveicolo;
    }

    public Map<String,Veicolo> allVeicoloAutista(String autista) {
        Map<String,Veicolo> veicoliauti = new HashMap<>();
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
                    veicoliauti.putIfAbsent(veicolo.getTarga(), veicolo);
                    System.out.println(veicoliauti);
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
