package fr.gravendev.database;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class AData<T> {

    private final DatabaseManager databaseManager;

    public AData(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public boolean save(T obj) {
        try (Connection connection = getConnection()) {
            return save(obj, connection);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    protected abstract boolean save(T obj, Connection connection) throws SQLException;

    public T get(String value) {
        try (Connection connection = getConnection()) {
            return get(value, connection);
        } catch (SQLException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    protected abstract T get(String value, Connection connection) throws SQLException;

    public void delete(T obj) {
        try (Connection connection = getConnection()) {
            delete(obj, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected abstract void delete(T obj, Connection connection) throws SQLException;

    protected Connection getConnection() throws SQLException {
        return databaseManager.getConnection();
    }
}
