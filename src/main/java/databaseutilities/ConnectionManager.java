package databaseutilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    public Connection connectToDatabase() {

        String dbUrl = ApplicationConfig.readFromConfig("Url");
        String dbPort = ApplicationConfig.readFromConfig("Port");
        String dbUserName = ApplicationConfig.readFromConfig("Username");
        String dbPassword = ApplicationConfig.readFromConfig("Password");
        String database = ApplicationConfig.readFromConfig("DatabaseSchema");

        Connection connection;
        String MYSQL_Driver = "com.mysql.cj.jdbc.Driver";//mysql


        try {
            Class.forName(MYSQL_Driver).newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String mySQLConnectionURL = "jdbc:mysql://" + dbUrl + ":" + dbPort + "/" + database + "?useSSL=false";
        try {
            connection = DriverManager.getConnection(mySQLConnectionURL, dbUserName, dbPassword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if (!connection.isClosed()) {
                System.out.println("Database connection is established!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;

    }

    public void closeDatabaseConnection(Connection connection) {
        try {
            connection.isClosed();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Connection is closed!!");

    }
}
