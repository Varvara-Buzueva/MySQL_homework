package dbo;

public class GroupWithCurator {
    private final int id;
    private final String name;
    private final String curatorFio;

    public static String groupTableName = "groups_name";
    public static String curatorTableName = "curators";

    public GroupWithCurator(int id, String name, String curatorFio){
        this.id = id;
        this.name = name;
        this.curatorFio = curatorFio;
    }

    public int getId() { return this.id; }
    public String getName(){
        return this.name;
    }
    public String getCuratorFio() { return this.curatorFio; }
}
