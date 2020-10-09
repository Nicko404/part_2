package com.sakovich.bd;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public enum DataSourceUtil {

    INSTANCE;

    private Connection[] connections = null;

    DataSourceUtil() {}

    public Connection getConnection() {
        if (connections == null) {
            initializeDbConnection();
        }

        Connection connection = null;

        for (int i = 0; i < connections.length; i++) {
            if (connections[i] != null) {
                connection = connections[i];
                connections[i] = null;
                break;
            }
        }
        return connection;
    }

    public void returnConnection(Connection connection) {
        for (int i = 0; i < connections.length; i++) {
            if (connections[i] == null) {
                connections[i] = connection;
                return;
            }
        }
    }

    private void initializeDbConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        connections = new Connection[2];

        try (InputStream resources = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("application.properties")) {

            Properties properties = new Properties();
            properties.load(resources);

            String url = properties.getProperty("db.url");
            String user = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");

            while (hasFreeSlots()) {
                Connection connection = DriverManager.getConnection(url, user, password);
                returnConnection(connection);
            }
            System.out.println("Connection est");

        } catch (IOException | SQLException e) {
            System.out.println("Connection Error");
        }
    }

    private boolean hasFreeSlots() {
        boolean isFreeSlotsAvailable = false;

        for (int i = 0; i < connections.length; i++) {
            if (connections[i] == null) {
                isFreeSlotsAvailable = true;
                break;
            }
        }
        return isFreeSlotsAvailable;
    }
}
