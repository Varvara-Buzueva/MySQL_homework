package utils.resurces;

import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadPropertiesFromPropsFile implements IReadProperty <Properties>{

    @Override
    public Properties read() {
        String rootFolder = System.getProperty("user.dir"):

        try (InputStream imput = new FileInputStream(String.format("%s/src/resources/%", rootFolder,"db.properties"))){
            Properties prop = new Properties();
            prop.load(imput);


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
