package db;

import java.sql.ResultSet;

public interface IDbExecutor {
   ResultSet execute(String sqlRequest);
   void add(String tableName, String newElement);
   void create(String tableName, String dataDefinitionRequest);
   ResultSet get(String tableName);
   ResultSet get(String tableName, String column, String value);
   int getCount(String tableName);
   ResultSet getStudentsFromGroup(String[] tables, String[] columns, String groupName);
   boolean update(String tableName, String column, String newValue, String oldValue);
   ResultSet leftJoin(String[] columnsName, String[] tables, String firstTableColumn, String secondTableColumn);
   ResultSet multipleLeftJoin(String[] columnsName, String[] tables, String[] primaryKeys, String[] foreignKeys);
   void close();
}
