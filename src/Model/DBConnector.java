package Model;
import java.sql.*;

public class DBConnector {

    public static Connection getConnection() {
        Connection db = null;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (java.lang.ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        String url = "jdbc:postgresql://manny.db.elephantsql.com:5432/piqcjyuk";
        String username = "piqcjyuk";
        String password = "QWWO-eGglEY1yXKMPTh-_ZN2gF4ZvyJz";

        try {
            db = DriverManager.getConnection(url, username, password);
        } catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
        }
        return db;
    }
}

