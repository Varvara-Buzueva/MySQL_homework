package db;

import dbo.Curator;
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
            String request = String.format("SET foreign_key_checks = 0;");
            statement.execute(request);
            request = String.format("DROP TABLE IF EXISTS %s;", tableName);
            statement.execute(request);
            request = String.format("SET foreign_key_checks = 1;");
            statement.execute(request);
            request = String.format("CREATE TABLE %s(%s);",tableName, dataDefinitionRequest);
            statement.execute(request);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public ResultSet get(String tableName){
        String request = String.format("select * from %s;", tableName);
        return execute(request);
    }

    public ResultSet get(String tableName, String condition){
        String request = String.format("select * from %s where %s;", tableName, condition);
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

    public ResultSet execute(String sqlRequest) {
        ResultSet resultSet = null;

        try {
            resultSet = statement.executeQuery(sqlRequest);
        }catch (SQLException ex){
            ex.printStackTrace();
        }

        return resultSet ;
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

    public boolean update(String tableName, String column, String newValue, String condition){
        String request = String.format("update %s set %s = %s where %s;", tableName, column, newValue, condition );
        try{
            statement.execute(request);
            return true;
        } catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }
}
