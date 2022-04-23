package dbo;

public class Student {
    public final static String tableName ="students";
    public final static String fileName ="students.table";

    private int id;
    private String fio;
    private String sex;
    private int groupId;

    public Student(int id, String fio, String sex, int groupId){
        this.id = id;
        this.fio = fio;
        this.sex = sex;
        this.groupId = groupId;
    }


    public String getFio(){
        return this.fio;
    }
    public String getSex() { return this.sex; }

    public int getId() { return this.id; }
    public int getGroupId() { return this.groupId;  }
}
