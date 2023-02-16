package SL_db;

import model.ViaggioProgrammato;

import java.sql.*;

public class ViaggioProgrammatoDAO {
    public void insertViaggio(ViaggioProgrammato viaggio) {
        String sql = "insert into viaggi_programmati values (?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            Connection conn = DBConnect.getConnection();
            if(conn!=null) {
                System.out.println("connessione con successo");
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, viaggio.getAutista().getUsername());
                statement.setString(2, viaggio.getVeicolo().getTarga());
                statement.setString(3, viaggio.getAddress().getCitta_partenza();
                statement.setString(4, viaggio.getAddress().getCitta_destinazione());
                statement.setDate(5, (Date) viaggio.getData_partenza());
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


}
