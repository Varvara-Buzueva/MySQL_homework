package utils.resources;

import dbo.Curator;

public class ReadCuratorsTable extends ReadTableData<Curator>{
    @Override
    protected Curator getElement(String[] data) {
        return new Curator(new Integer(data[0]), data[1]);
    }
}
