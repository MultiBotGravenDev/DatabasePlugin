package fr.gravendev.database;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseManager {

    private final DatabaseConnection databaseConnection;

    public DatabaseManager(String host, String username, String password, String database) {
        this.databaseConnection = DatabaseConnection.Builder
                .aDatabaseConnection()
                .withHost(host)
                .withUser(username)
                .withPassword(password)
                .withDatabase(database)
                .build();
    }

    public Connection getConnection() throws SQLException {
        return databaseConnection.getConnection();
    }
}
