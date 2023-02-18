package SL_db;

import model.Veicolo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import model.*;
import model.Utente;
import model.Veicolo;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class RecensioneCorsaDAO {

    public void insertRecensione(CorsaViaggio viaggio) {
        //Utente user = new Utente();
        String sql = "insert into recensioni_corsa values (?,?,?)";

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
        String sql = "select * from recensioni_corsa join viaggi_programmati on recensioni_viaggi.viaggio=viaggi_programmati.ID where autista= ?";

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
}
