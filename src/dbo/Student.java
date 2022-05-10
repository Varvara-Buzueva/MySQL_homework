package dbo;

public class Student {
    private final int id;
    private final String fio;
    private final String sex;
    private final int groupId;

    public final static String tableName ="students";
    public final static String fileName ="students.table";

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
