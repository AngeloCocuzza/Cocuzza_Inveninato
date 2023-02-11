package SL_db;

import model.Autista;
import model.Veicolo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VeicoloDao {
    public void insertVeicolo(Veicolo veicol) {
        //Utente user = new Utente();
        String sql = "insert into patente values (?,?,?,?,?,?)";

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
                statement.setString(6, veicol.getN_posti());

                statement.executeUpdate();
            } else {
                System.out.println("connessione fallita");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
