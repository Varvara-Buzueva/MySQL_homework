package dbo;

public abstract class bdoAbs {
    protected String tableName="";
    public bdoAbs (String tableName)  {
        this.tableName = tableName;
    }
}
