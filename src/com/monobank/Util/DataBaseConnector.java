package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnector {

    private static final String DB_DRIVER = "org.postgresql.Driver";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres?currentSchema=mono";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "password";
    private static Connection connection;

    public Connection getConnection() {
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException exception) {
            exception.printStackTrace();
        }

        return connection;
    }
}
