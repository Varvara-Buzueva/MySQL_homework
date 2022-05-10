package tables;

import dbo.Curator;
import utils.resources.ReadCuratorsTable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public  class CuratorsTable extends TableAbs  implements ITable<Curator>{
    public CuratorsTable(String dbType) {
        super(dbType);
        dbExecutor.create(Curator.tableName, "id int NOT NULL, fio VARCHAR(50) NOT NULL, PRIMARY KEY (`id`)");
    }

    @Override
    public void fill() {
        ReadCuratorsTable reader = new ReadCuratorsTable();
        List<Curator> data = reader.read(Curator.fileName);
        for(Curator curator: data){
            dbExecutor.add(Curator.tableName, String.format("%s, '%s'", curator.getId(), curator.getFio()));
        }
    }

    @Override
    public List<Curator> list() {
        ResultSet resultSet = dbExecutor.get(Curator.tableName);
        List<Curator> curators = new ArrayList<>();
        try {
            while (resultSet.next()){
                curators.add(new Curator(
                        resultSet.getInt(1),
                        resultSet.getString(2)
                ));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }

        return curators;
        }
    }


