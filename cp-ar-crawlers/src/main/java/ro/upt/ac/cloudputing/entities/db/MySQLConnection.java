package ro.upt.ac.cloudputing.entities.db;

import java.sql.Connection;
import java.sql.DriverManager;

// models the MySQL connection with parameters
public class MySQLConnection {
    private Connection connection = null;

    public MySQLConnection() {
        try {
            var dbUser = System.getProperty("DB_USER");
            if (dbUser == null) dbUser = "root";

            var dbPassword = System.getProperty("DB_PASSWORD");
            if (dbPassword == null) dbPassword = "";

            var dbUrl = System.getProperty("DB_URL");
            if (dbUrl == null)
                dbUrl = "jdbc:mysql://localhost:3306/cp_rezultate_academice?useUnicode=true&characterEncoding=UTF-8";

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
