package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AlumnosDB {
    public static Connection conn() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:h2:./databases/alumnos", "ect", "ect");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {}
        return conn;
    }
}


