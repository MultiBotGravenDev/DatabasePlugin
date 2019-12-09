package fr.gravendev;

import fr.gravendev.database.DatabaseManager;
import fr.neutronstars.nbot.api.event.EventPriority;
import fr.neutronstars.nbot.api.event.Listener;
import fr.neutronstars.nbot.api.event.server.NBotServerStartingEvent;
import fr.neutronstars.nbot.api.plugin.NBotPlugin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabasePlugin extends NBotPlugin {

    private DatabaseManager databaseManager;

    public DatabasePlugin() {
        super("database_plugin", "Database Plugin", "1.0", "Manage requests with database", "Nolan");
    }

    @Listener(priority = EventPriority.LOWEST)
    public void onStarting(NBotServerStartingEvent event) throws IOException {
        super.getLogger().info("Starting of " + super.getName() + "...");

        String host = super.getConfiguration().getOrSet("localhost", "database", "host");
        String username = super.getConfiguration().getOrSet("", "database", "username");
        String password = super.getConfiguration().getOrSet("", "database", "password");
        String database = super.getConfiguration().getOrSet("multibot", "database", "database");
        super.getConfiguration().save();

        this.databaseManager = new DatabaseManager(host, username, password, database);
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public Connection getConnection() throws SQLException {
        return this.databaseManager.getConnection();
    }
}
