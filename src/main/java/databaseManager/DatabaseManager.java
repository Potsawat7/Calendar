package databaseManager;

import java.sql.ResultSet;

public interface DatabaseManager {
     void addToTable(String q);
     void deleteFromTable(String q);
     void estDatabase();
     void closeDatabase();
     ResultSet selectFromDatabase(String q);
}
