package db;

import utils.resurces.IReadProperty;
import utils.resurces.ReadPropertiesFromPropsFile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class MySqlDbExecutor implements IDbExecutor{

    @Override
    public ResultSet execute(String sqlRequest) {
        IReadProperty<Properties> readerProps = new ReadPropertiesFromPropsFile();
        Properties properties =  readerProps.read();


        try {
            Connection connect = DriverManager.getConnection(
                    properties.getProperty("url") + "/" + properties.getProperty("db_name"),
                    properties.getProperty("username"),
                    properties.getProperty("passwqrd")
            );
        }catch (SQLException e)


        return null;
    }
}
