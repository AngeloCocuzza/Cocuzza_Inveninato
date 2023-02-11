package SL_db;

import model.Autista;
import model.Patente;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PatenteDao {
    public void insertPatente(Patente patent) {
        //Utente user = new Utente();
        String sql = "insert into patente values (?,?,?,?,?)";

        try {
            Connection conn = DBConnect.getConnection();
            if(conn!=null) {
                System.out.println("connessione con successo");
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, patent.getCodice());
                statement.setString(2, patent.getAutista());
                statement.setString(3, String.valueOf((Date) patent.getData_conseguimento()));
                statement.setString(4, String.valueOf((Date) patent.getData_scadenza()));
                statement.setString(5, patent.getLivello());

                statement.executeUpdate();
            } else {
                System.out.println("connessione fallita");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
