package dbo;

public class Studets extends bdoAbs{

    //private String tableName ="Students";
    public Studets(int id, int id_group, String fio, String sex){
        super("Students");
        this.id = id;
        this.id_group = id;
        this.fio = fio;
        this.sex = sex;
    }

    private String fio;
    private String sex;
    private int id;
    private int id_group;

    public String getFio(){
        return this.fio;
    }
    public String getSex(){
        return this.sex;
    }

    public int getId(){
        return this.id;
    }

    public int getId_group(){
        return this.id_group;
    }
}
