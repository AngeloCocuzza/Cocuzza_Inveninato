package SL_db;

import model.Utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UtenteDAO {
    public Utente insertUser() {
        //Utente user = new Utente();
        String sql = "insert into utenti values (?, ?, ...)";

        try {
            Connection conn = DBConnect.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            //statement.setInt(1, employee.getId());
            //statement.setString(2, employee.getFullName().getFirstName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}
