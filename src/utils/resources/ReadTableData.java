package utils.resources;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class ReadTableData<T> implements IReadList<T> {
    protected abstract T getElement(String[] data);

    public List<T> read(String filename){
        String rootFolder = System.getProperty("user.dir");
        String filePath = String.format("%s\\src\\resources\\%s", rootFolder, filename);
        List<T> result = new ArrayList<>();
        String row;
        File csvFile = new File(filePath);
        BufferedReader csvReader = null;
        if (csvFile.isFile()) {
            try{
                try {
                    csvReader = new BufferedReader(new FileReader(filePath));
                } catch (FileNotFoundException ex){
                    ex.printStackTrace();
                }

                while (csvReader != null && (row = csvReader.readLine()) != null) {
                    String[] data = row.split(";");
                    T element = getElement(data);
                    result.add(element);
                }
                if (csvReader != null){
                    csvReader.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    return result;
    }
}
