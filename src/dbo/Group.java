package dbo;

public class Group {
  private final int id;
  private final String name;
  private final int curatorId;

  public final static String tableName ="groups_name";
  public final static String fileName = "groups.table";

  public Group(int id, String name, int curatorId){
    this.id = id;
    this.name = name;
    this.curatorId = curatorId;
  }

    public int getId() { return this.id; }
    public String getName(){
    return this.name;
  }
    public int getCuratorId() { return this.curatorId; }
}
