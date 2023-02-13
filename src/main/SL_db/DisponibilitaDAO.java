package SL_db;

import model.Disponibilita;
import model.Veicolo;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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

    public List<Disponibilita> allDisponibilita() {
        List<Disponibilita> alldisp = new ArrayList<>();
        String sql = "select * from disponibilita";

        try {
            Connection conn = DBConnect.getConnection();
            if(conn!=null) {
                System.out.println("connessione con successo");
                PreparedStatement statement = conn.prepareStatement(sql);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    Disponibilita dispo = new Disponibilita(rs.getString("autista"), rs.getDate("giorno_disponibilita"), LocalTime.parse(rs.getString("ora_inizio")), LocalTime.parse(rs.getString("ora_fine")),rs.getString("citta_partenza"));
                    alldisp.add(dispo);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return alldisp;
    }
}
