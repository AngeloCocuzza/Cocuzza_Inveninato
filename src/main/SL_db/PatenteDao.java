package SL_db;

import model.Autista;
import model.Patente;
import model.Veicolo;

import java.sql.*;

public class PatenteDao {

    public static PatenteDao patentedao;

    public static PatenteDao getInstance() {
        if (patentedao == null)
            patentedao = new PatenteDao();
        else
            System.out.println("Istanza già creata");
        return patentedao;
    }
    public Patente insertPatente(Patente patent) {
        //Utente user = new Utente();
        String sql = "insert into patente values (?,?,?,?,?)";

        try {
            Connection conn = DBConnect.getConnection();
            if(conn!=null) {
                System.out.println("connessione con successo");
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, patent.getCodice());
                statement.setString(2, patent.getAutista());
                statement.setDate(3, (Date) patent.getData_conseguimento());
                statement.setDate(4, (Date) patent.getData_scadenza());
                statement.setString(5, patent.getLivello());

                statement.executeUpdate();
            } else {
                System.out.println("connessione fallita");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return patent;
    }

    public Patente selectPatenteByAutista(String autista) {
        String sql = "select * from patente where autista = ?";

        try {
            Connection conn = DBConnect.getConnection();
            if(conn!=null) {
                System.out.println("connessione con successo");
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1,autista);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    Patente patente = new Patente(rs.getString("codice"), rs.getString("autista"), rs.getDate("data_conseguimento"), rs.getDate("data_scadenza"),rs.getString("livello"));
                    return patente;
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

}
