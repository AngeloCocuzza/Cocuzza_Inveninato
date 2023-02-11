package SL_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    public static Connection getConnection() throws SQLException {
        String jdbcURL= "jdbc:mysql://localhost:3306/shuttlelive?user=root";
        return DriverManager.getConnection(jdbcURL);
    }
}
