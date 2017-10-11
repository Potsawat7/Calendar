package databaseManager;

import java.sql.*;

public class sqliteDB implements databaseManager {

    Connection conn;
    Statement statement;
    @Override
    public void addToTable(String q) {
        if (conn != null) {
            createState(q);

            closeDatabase();
        }
    }

    @Override
    public void deleteFromTable(String query) {
        if (conn != null) {
            createState(query);

            closeDatabase();
        }
    }

    @Override
    public void estDatabase() {

        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:appointment.db";
            conn = DriverManager.getConnection(dbURL);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeDatabase() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ResultSet selectFromDatabase(String query) {

        ResultSet resultSet = null;
        try {
            if (conn != null) {
                statement = conn.createStatement();
                resultSet = statement.executeQuery(query);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    public void createState(String query){

        try {
            statement = conn.createStatement();
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
