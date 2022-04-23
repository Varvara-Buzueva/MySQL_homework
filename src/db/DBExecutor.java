package db;

import java.sql.ResultSet;
import java.util.Locale;

public class DBExecutor implements IDbExecutor{
    private static DBExecutor instance;
    private static IDbExecutor executor;

    private DBExecutor(String dbType) {
        switch (dbType.toLowerCase(Locale.ROOT)) {
            case "mysql": {
                executor = new MySqlDbExecutor();
                break;
            }
        }
    }

    public void add(String tableName, String newElement){
        ResultSet resultSet = null;
        if (instance != null) {
            executor.add(tableName, newElement);
        }
    }

    public void create(String tableName, String dataDefinitionRequest) {
        if(instance != null){
            executor.create(tableName, dataDefinitionRequest);
        }
    }

    public void close() {
        if (instance != null) {
            executor.close();
            instance = null;
        }

    }

    public ResultSet execute(String sqlRequest) {
        ResultSet resultSet = null;
        if (instance != null) {
            resultSet = executor.execute(sqlRequest);
        }
        return resultSet;
    }

    public ResultSet get(String tableName){
        ResultSet resultSet = null;
        if (instance != null) {
            resultSet = executor.get(tableName);
        }
        return resultSet;
    }

    public ResultSet get(String tableName, String condition){
        ResultSet resultSet = null;
        if (instance != null) {
            resultSet = executor.get(tableName, condition);
        }
        return resultSet;
    }

    public int getCount(String tableName){
        int count = 0;
        if (instance != null) {
            count = executor.getCount(tableName);
        }
        return count;
    }

    public static synchronized DBExecutor getInstance(String dbType) {
        if (instance == null) {
            instance = new DBExecutor(dbType);
        }

        return instance;
    }

    public ResultSet leftJoin(String[] columnsName, String[] tables, String firstTableColumn, String secondTableColumn){
        ResultSet resultSet = null;
        if (instance != null) {
            resultSet = executor.leftJoin(columnsName, tables, firstTableColumn, secondTableColumn);
        }
        return resultSet;
    }

    public boolean update(String tableName, String column, String newValue, String condition){
        boolean isUpdated = false;
        if (instance != null) {
            isUpdated = executor.update(tableName, column, newValue, condition);
        }
        return isUpdated;
    }
}
