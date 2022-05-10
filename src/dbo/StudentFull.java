package dbo;

import utils.resources.StringHelper;
import java.util.List;

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

    public static void render(List<StudentFull> items){
        // Рендер шапки таблицы
        System.out.println("┌----┬--------------------------------┬-----┬-------------------------------------------┬--------------------------------┐");
        System.out.println("│ id │ fio                            │ sex │ group name                                │ curator fio                    │");
        System.out.println("├----┼--------------------------------┼-----┼-------------------------------------------┼--------------------------------┤");
        // Рендер строк таблицы
        int index = 0;
        for (StudentFull item: items){
            index++;
            String id = Integer.toString(item.getId());
            if (id.length() < 2){
                id = " " + id;
            }
            String fio = item.getFio() + StringHelper.getSpaces(30 - item.getFio().length());
            String sex = item.getSex();
            String groupName = item.getGroupName() + StringHelper.getSpaces(41 - item.getGroupName().length());
            String curatorFio = item.getCuratorFio() + StringHelper.getSpaces(30 - item.getCuratorFio().length());
            System.out.printf("| %s | %s | %s | %s | %s |%n", id, fio, sex, groupName, curatorFio);
            if (index == items.size()){
                // Рендер последней строки
                System.out.println("└----┴--------------------------------┴-----┴-------------------------------------------┴--------------------------------┘");
            } else {
                System.out.println("├----┼--------------------------------┼-----┼-------------------------------------------┼--------------------------------┤");
            }

        }
    }
}
