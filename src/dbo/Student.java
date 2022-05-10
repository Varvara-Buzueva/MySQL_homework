package dbo;

import utils.resources.StringHelper;
import java.util.List;

public class Student {
    private final int id;
    private final String fio;
    private final String sex;
    private final int groupId;

    public final static String tableName = "students";
    public final static String fileName = "students.table";

    public Student(int id, String fio, String sex, int groupId) {
        this.id = id;
        this.fio = fio;
        this.sex = sex;
        this.groupId = groupId;
    }


    public int getId() {
        return this.id;
    }

    public String getFio() {
        return this.fio;
    }

    public String getSex() {
        return this.sex;
    }

    public int getGroupId() {
        return this.groupId;
    }

    public static void render(List<Student> items) {
        // Рендер шапки таблицы
        System.out.println("┌----┬--------------------------------┬-----┬---------┐");
        System.out.println("│ id │ fio                            │ sex │ groupId │");
        System.out.println("├----┼--------------------------------┼-----┼---------┤");
        // Рендер строк таблицы
        int index = 0;
        for (Student item : items) {
            index++;
            String id = Integer.toString(item.getId());
            if (id.length() < 2) {
                id = " " + id;
            }
            String fio = item.getFio() + StringHelper.getSpaces(30 - item.getFio().length());
            String sex = item.getSex();
            String groupId = StringHelper.getSpaces(7 - Integer.toString(item.getGroupId()).length()) + item.getGroupId();
            System.out.printf("| %s | %s | %s | %s |%n", id, fio, sex, groupId);
            if (index == items.size()) {
                // Рендер последней строки
                System.out.println("└----┴--------------------------------┴-----┴---------┘");
            } else {
                System.out.println("├----┼--------------------------------┼-----┼---------┤");
            }

        }
    }
}
