import db.DBExecutor;
import dbo.Curator;
import dbo.Group;
import dbo.GroupWithCurator;
import dbo.Student;
import tables.CuratorsTable;
import tables.GroupTable;
import tables.StudentsTable;

import java.util.List;


public class Main {

    public static void main (String...args) {
        // Создание таблиц
        CuratorsTable curatorsTable = new CuratorsTable("mysql");
        GroupTable groupTable = new GroupTable("mysql");
        StudentsTable studentsTable = new StudentsTable("mysql");
        // Заполнение таблиц
        curatorsTable.fill();
        groupTable.fill();
        studentsTable.fill();

        List<Curator> curators = curatorsTable.list();
        List<Group> groups = groupTable.list();
        List<Student> students = studentsTable.list();


        for (Curator curator : curators) {
            System.out.println(curator.getId());
            System.out.println(curator.getFio());
        }

        for (Group group: groups){
            System.out.println(group.getId());
            System.out.println(group.getName());
        }
        for (Student student : students) {
            System.out.println(student.getId());
            System.out.println(student.getFio());
            System.out.println(student.getSex());
            System.out.println(student.getGroupId());
        }

        List<GroupWithCurator> groupWithCurators = groupTable.getGroupWithCurator();

        for (GroupWithCurator groupWithCurator :groupWithCurators){
            System.out.println(groupWithCurator.getId());
            System.out.println(groupWithCurator.getName());
            System.out.println(groupWithCurator.getCuratorFio());

        }
/*
        List<GroupWithCurator> newGroupWithCurators = groupTable.setNewCurator("1", "4");

        for (GroupWithCurator groupWithCurator :newGroupWithCurators){
            System.out.println(groupWithCurator.getId());
            System.out.println(groupWithCurator.getName());
            System.out.println(groupWithCurator.getCuratorFio());

        }
*/
    }
}


