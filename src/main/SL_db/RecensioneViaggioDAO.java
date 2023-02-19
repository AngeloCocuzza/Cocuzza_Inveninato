package SL_db;

import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecensioneViaggioDAO {

    public void insertRecensione(CorsaViaggio viaggio) {
        //Utente user = new Utente();
        String sql = "insert into recensioni_viaggi values (?,?,?)";

        try {
            Connection conn = DBConnect.getConnection();
            if(conn!=null) {
                System.out.println("connessione con successo");
                PreparedStatement statement = conn.prepareStatement(sql);

                statement.setInt(1, viaggio.getRecensione().getVoto());
                statement.setString(2, viaggio.getRecensione().getCommento());
                statement.setInt(3, viaggio.getID());


                statement.executeUpdate();
            } else {
                System.out.println("connessione fallita");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public List<Recensione> allRecensioniAutista(String autista) {

        List<Recensione> listaRecen=new ArrayList<>();
        String sql = "select * from recensioni_viaggi join viaggi_programmati on recensioni_viaggi.viaggio=viaggi_programmati.ID where autista= ?";

        try {
            Connection conn = DBConnect.getConnection();
            if(conn!=null) {
                System.out.println("connessione con successo");
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1,autista);
                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    Recensione review = new Recensione(rs.getInt("voto"), rs.getString("commento"));
                    listaRecen.add(review);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaRecen;
    }

    public Recensione recensioneViaggio(ViaggioProgrammato viaggio) {

        String sql = "select * from recensioni_viaggi join corsa_programmati on recensioni_viaggi.viaggio=corsa_programmati.ID_viaggio where ID_viaggio= ? limit 1";

        try {
            Connection conn = DBConnect.getConnection();
            if(conn!=null) {
                System.out.println("connessione con successo");
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1,viaggio.getID());
                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    Recensione review = new Recensione(rs.getInt("voto"), rs.getString("commento"));
                    return review;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
