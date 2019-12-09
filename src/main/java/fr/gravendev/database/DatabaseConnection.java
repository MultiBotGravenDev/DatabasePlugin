package fr.gravendev.database;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

class DatabaseConnection {

    private final HikariDataSource dataSource;

    private DatabaseConnection(Builder builder) {
        this.dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://" + builder.host + ":3306/" + builder.database);
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername(builder.user);
        dataSource.setPassword(builder.password);
        dataSource.addDataSourceProperty("autoReconnect", true);
        dataSource.addDataSourceProperty("tcpKeepAlive", true);
        dataSource.addDataSourceProperty("serverTimezone", "Europe/Paris");
        dataSource.addDataSourceProperty("characterEncoding", "utf8");
        dataSource.addDataSourceProperty("useUnicode", "true");
        dataSource.setMaximumPoolSize(15);
        dataSource.setMinimumIdle(0);
    }

    Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }

    static class Builder {

        String host, user, password, database;

        static Builder aDatabaseConnection() {
            return new Builder();
        }

        Builder withHost(String host) {
            this.host = host;
            return this;
        }

        Builder withUser(String user) {
            this.user = user;
            return this;
        }

        Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        Builder withDatabase(String database) {
            this.database = database;
            return this;
        }

        DatabaseConnection build() {
            return new DatabaseConnection(this);
        }

    }

}
