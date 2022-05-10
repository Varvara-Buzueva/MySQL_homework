import dbo.*;
import tables.CuratorsTable;
import tables.GroupTable;
import tables.StudentsTable;

import java.util.List;


public class Main {

    public static void main (String...args) {
        System.out.println("п.1,2,3 - Создание таблиц");
        CuratorsTable curatorsTable = new CuratorsTable("mysql");
        GroupTable groupTable = new GroupTable("mysql");
        StudentsTable studentsTable = new StudentsTable("mysql");

        System.out.println("п.4 - Заполнение таблиц.");
        curatorsTable.fill();
        groupTable.fill();
        studentsTable.fill();
        List<Curator> curators = curatorsTable.list();
        List<Group> groups = groupTable.list();
        List<Student> students = studentsTable.list();
        System.out.println("Таблица curators");
        Curator.render(curators);
        System.out.println("Таблица group_names");
        Group.render(groups);
        System.out.println("Таблица students");
        Student.render(students);

        System.out.println("п.5 -  Вывести на экран информацию о всех студентах включая название группы и имя куратора.");
        List<StudentFull> fullList = studentsTable.getFullList();

        StudentFull.render(fullList);

        System.out.println("п.6 - Вывести на экран количество студентов.");
        int count = studentsTable.getStudentsCount();
        System.out.println(count);

        System.out.println("п.7 - Вывести студенток.");
        List<Student> females = studentsTable.getFemales();
        Student.render(females);

        System.out.println("п.8 - Обновить данные по группе сменив куратора.");
        System.out.println("Таблица групп до изменения куратора:");
        Group.render(groups);
        System.out.println("Группа после изменения куратора:");
        groups = groupTable.setNewCurator("1", "4");
        Group.render(groups);

        System.out.println("п.9 - Вывести список групп с их кураторами.");
        List<GroupWithCurator> groupWithCurators = groupTable.getGroupWithCurator();
        GroupWithCurator.render(groupWithCurators);

        // п.10 - Используя вложенные запросы вывести на экран студентов из определённой группы (поиск по имени группы)

        List<Student> studentsFromGroup = studentsTable.getStudentsFromGroup("Динамика полёта и управление движением КА");
        System.out.println("п.10 - Используя вложенные запросы вывести на экран студентов из определённой группы (поиск по имени группы)");
        Student.render(studentsFromGroup);



    }
}


