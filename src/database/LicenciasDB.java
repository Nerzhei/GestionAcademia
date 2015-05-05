package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LicenciasDB {
    public static Connection conn() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:h2:./databases/licencias", "ect", "ect");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}


