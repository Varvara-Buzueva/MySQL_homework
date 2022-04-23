package db;

import java.sql.ResultSet;

public interface IDbExecutor {
   ResultSet execute(String sqlRequest);
   void add(String tableName, String newElement);
   void create(String tableName, String dataDefinitionRequest);
   ResultSet get(String tableName);
   ResultSet get(String tableName, String condition);
   int getCount(String tableName);
   boolean update(String tableName, String column, String newValue, String condition);
   ResultSet leftJoin(String[] columnsName, String[] tables, String firstTableColumn, String secondTableColumn);
   void close();
}
