package databaseManager;

import java.sql.ResultSet;

public interface databaseManager {
     void addToTable(String q);
     void deleteFromTable(String q);
     void estDatabase();
     void closeDatabase();
     ResultSet selectFromDatabase(String q);
}
