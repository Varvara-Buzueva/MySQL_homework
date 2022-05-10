package dbo;

public class StudentFull {
    private final int id;
    private final String fio;
    private final String sex;
    private final String groupName;
    private final String curatorFio;

    public static String groupTableName = "groups_name";
    public static String curatorTableName = "curators";
    public static String studentsTableName ="students";

    public StudentFull (int id, String fio, String sex, String groupName, String curatorFio){
        this.id = id;
        this.fio = fio;
        this.sex = sex;
        this.groupName = groupName;
        this.curatorFio = curatorFio;
    }

    public int getId() { return this.id; }
    public String getFio(){
        return this.fio;
    }
    public String getSex() { return this.sex; }
    public String getGroupName() { return this.groupName; }
    public String getCuratorFio() { return this.curatorFio; }
}
