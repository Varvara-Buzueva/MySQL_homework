import db.DBExecutor;
import dbo.*;
import tables.CuratorsTable;
import tables.GroupTable;
import tables.StudentsTable;

import java.util.List;


public class Main {

    public static void main (String...args) {
        // п.1,2,3 - Создание таблиц
        CuratorsTable curatorsTable = new CuratorsTable("mysql");
        GroupTable groupTable = new GroupTable("mysql");
        StudentsTable studentsTable = new StudentsTable("mysql");
        // п.4 - Заполнение таблиц
        curatorsTable.fill();
        groupTable.fill();
        studentsTable.fill();

        // п.5 -  Вывести на экран информацию о всех студентах включая название группы и имя куратора
        List<StudentFull> fullList = studentsTable.getFullList();

        for(StudentFull studentFull : fullList){
            System.out.println(studentFull.getId());
            System.out.println(studentFull.getFio());
            System.out.println(studentFull.getSex());
            System.out.println(studentFull.getGroupName());
            System.out.println(studentFull.getCuratorFio());
        }

        // п.6 - Вывести на экран количество студентов.
        int count = studentsTable.getStudentsCount();
        System.out.println(count);

        // п.7 - Вывести студенток.
        List<Student> females = studentsTable.getFemales();

        for(Student female : females){
            System.out.println(female.getId());
            System.out.println(female.getFio());
            System.out.println(female.getSex());
            System.out.println(female.getGroupId());
        }

        // п.8 - Обновить данные по группе сменив куратора.
        List<Group> groupWithNewCurator = groupTable.setNewCurator("1", "4");

        for (Group group : groupWithNewCurator){
            System.out.println(group.getId());
            System.out.println(group.getName());
            System.out.println(group.getCuratorId());
        }

        // п.9 - Вывести список групп с их кураторами.
        List<GroupWithCurator> groupWithCurators = groupTable.getGroupWithCurator();

        for (GroupWithCurator groupWithCurator :groupWithCurators){
            System.out.println(groupWithCurator.getId());
            System.out.println(groupWithCurator.getName());
            System.out.println(groupWithCurator.getCuratorFio());

        }


    }
}


