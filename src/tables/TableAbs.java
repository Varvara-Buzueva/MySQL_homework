package tables;

import db.DBExecutor;

public  abstract class TableAbs{

    protected static DBExecutor dbExecutor = null;

    public TableAbs(String dbType){
        dbExecutor = DBExecutor.getInstance(dbType);
    }
}
