package db;

import utils.resources.IReadProperty;
import utils.resources.ReadPropertiesFromPropsFile;

import java.sql.*;
import java.util.Properties;

public class MySqlDbExecutor implements IDbExecutor{

    private static Connection connect =null;
    private static Statement statement= null;

    public MySqlDbExecutor(){
        IReadProperty<Properties> readerProps = new ReadPropertiesFromPropsFile();
        Properties properties =  readerProps.read();

        try {
            connect = DriverManager.getConnection(
                    properties.getProperty("url") + "/" + properties.getProperty("db_name"),
                    properties.getProperty("username"),
                    properties.getProperty("password")
            );

            statement =  connect.createStatement();

        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public void add(String tableName, String newElement){
        try{
            String request = String.format("insert into %s values (%s);", tableName, newElement);
            statement.execute(request);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public void close(){
        try {
            connect.close();
            statement.close();
            connect = null;
            statement = null;
        } catch (SQLException ex){
            ex.printStackTrace();
        }

    }

    public void create(String tableName, String dataDefinitionRequest) {

        try{
            String request = "SET foreign_key_checks = 0;";
            statement.execute(request);
            request = String.format("DROP TABLE IF EXISTS %s;", tableName);
            statement.execute(request);
            request = "SET foreign_key_checks = 1;";
            statement.execute(request);
            request = String.format("CREATE TABLE %s(%s);",tableName, dataDefinitionRequest);
            statement.execute(request);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public ResultSet execute(String sqlRequest) {
        ResultSet resultSet = null;

        try {
            resultSet = statement.executeQuery(sqlRequest);
        }catch (SQLException ex){
            ex.printStackTrace();
        }

        return resultSet ;
    }

    public ResultSet get(String tableName){
        String request = String.format("select * from %s;", tableName);
        return execute(request);
    }

    public ResultSet get(String tableName, String column, String value){
        String request = String.format("select * from %s where %s = %s;", tableName, column, value);
        return execute(request);
    }

    public int getCount(String tableName){
        String request = String.format("select count(id) from %s;", tableName);
        int count = 0;
        try {
            ResultSet response = execute(request);
            response.next();
            count =response.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public ResultSet getStudentsFromGroup(String[] tables, String[] columns, String groupName){
        // SELECT * FROM Students WHERE groupId = (SELECT id FROM groups_name WHERE name = X);
        String request = String.format("select * from %s where %s = (select %s from %s where %s = %s);", tables[0], columns[0], columns[1], tables[1], columns[2], groupName);
        return  execute(request);
    }

    public ResultSet leftJoin(String[] columnsName, String[] tables, String firstTableColumn, String secondTableColumn){
        StringBuilder sb = new StringBuilder();
        String delimiter =", ";
        for ( String element : columnsName ) {
            if (sb.length() > 0) {
                sb.append( delimiter );
            }
            sb.append( element );
        }
        String columns = sb.toString();

        String request = String.format("select %s from %s left join %s on %s = %s;", columns, tables[0], tables[1], firstTableColumn, secondTableColumn);
        return execute(request);
    }

    public ResultSet multipleLeftJoin(String[] columnsName, String[] tables, String[] primaryKeys, String[] foreignKeys){

        StringBuilder sb = new StringBuilder();
        String delimiter =", ";
        for ( String element : columnsName ) {
            if (sb.length() > 0) {
                sb.append( delimiter );
            }
            sb.append( element );
        }
        String columns = sb.toString();
        String[] conditions = new String[2];
        for (int i = 0; i < 2; i++){
            conditions[i] = String.format("%s = %s", foreignKeys[i], primaryKeys[i]);
        }

        String request = String.format("SELECT %s FROM %s LEFT JOIN %s ON %s INNER JOIN %s ON %s;", columns, tables[0], tables[1], conditions[0], tables[2], conditions[1]);
        return execute(request);
    }

    public boolean update(String tableName, String column, String newValue, String oldValue){
        String request = String.format("update %s set %s = %s where %s = %s;", tableName, column, newValue, column, oldValue);
        System.out.println(request);
        try{
            statement.execute(request);
            return true;
        } catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }
}
