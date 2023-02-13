package SL_db;

import model.Disponibilita;

import java.sql.*;

public class DisponibilitaDAO {
    public void insertDisponibilita(Disponibilita disp) {
        String sql = "insert into disponibilita values (?,?,?,?,?)";

        try {
            Connection conn = DBConnect.getConnection();
            if(conn!=null) {
                System.out.println("connessione con successo");
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, disp.getAutista());
                statement.setDate(2, (Date) disp.getGiorno_disponibilita());
                statement.setTime(3, Time.valueOf(disp.getOra_inizio()));
                statement.setTime(4, Time.valueOf(disp.getOra_fine()));
                statement.setString(5, disp.getCitta_partenza());
                statement.executeUpdate();
            } else {
                System.out.println("connessione fallita");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
