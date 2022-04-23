package tables;

import db.DBExecutor;
import db.IDbExecutor;
// import dbo.Curators;
// import dbo.Students;

public  abstract class TableAbs{

    protected static DBExecutor dbExecutor = null;

    public TableAbs(String dbType){
        dbExecutor = DBExecutor.getInstance(dbType);
    }

}
