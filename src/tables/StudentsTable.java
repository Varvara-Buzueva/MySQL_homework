package tables;

import dbo.GroupWithCurator;
import dbo.Student;
import dbo.StudentFull;
import utils.resources.ReadStudentsTable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentsTable extends TableAbs implements ITable<Student>{

    private Student student;

    public StudentsTable(String dbType) {
        super(dbType);
         dbExecutor.create( Student.tableName,"id int NOT NULL, fio VARCHAR(50) NOT NULL, sex VARCHAR(3) NOT NULL, groupId int NOT NULL, PRIMARY KEY (`id`), FOREIGN KEY (`groupId`) REFERENCES `groups_name` (`id`) ON DELETE CASCADE ON UPDATE CASCADE");
    }

    public void fill() {
        ReadStudentsTable reader = new ReadStudentsTable();
        List<Student> data = reader.read(Student.fileName);

        for(Student student: data){
            dbExecutor.add(Student.tableName, String.format("%s, '%s', '%s', %s", student.getId(), student.getFio(), student.getSex(), student.getGroupId()));
        }
    }

    @Override
    public List<Student> list() {

        ResultSet resultSet = dbExecutor.get(Student.tableName);
        List<Student> students = new ArrayList<>();

        try {
            while (resultSet.next()) {
                students.add(new Student(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4 )
                ));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return students;
    }

    public List<StudentFull> getFullList(){
        String[] columnNames = new String[5];
        columnNames[0] = String.format("%s.%s", StudentFull.studentsTableName, "id");
        columnNames[1] = String.format("%s.%s", StudentFull.studentsTableName, "fio");
        columnNames[2] = String.format("%s.%s", StudentFull.studentsTableName, "sex");
        columnNames[3] = String.format("%s.%s", StudentFull.groupTableName, "name");
        columnNames[4] = String.format("%s.%s", StudentFull.curatorTableName, "fio");
        String[] tables = new String[3];
        tables[0] = StudentFull.studentsTableName;
        tables[1] = StudentFull.groupTableName;
        tables[2] = StudentFull.curatorTableName;
        String[] primaryKeys = new String[2];
        primaryKeys[0] = String.format("%s.%s", StudentFull.groupTableName, "id");
        primaryKeys[1] = String.format("%s.%s", StudentFull.curatorTableName, "id");
        String[] foreignKeys = new String[2];
        foreignKeys[0] = String.format("%s.%s", StudentFull.studentsTableName, "groupId");
        foreignKeys[1] = String.format("%s.%s", StudentFull.groupTableName, "curatorId");

        ResultSet resultSet = dbExecutor.multipleLeftJoin(columnNames, tables, primaryKeys, foreignKeys);
        List<StudentFull> studentFull = new ArrayList<>();

        try {
            while (resultSet.next()) {
                studentFull.add (new StudentFull(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                ));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return studentFull;
    }

    public int getStudentsCount(){
        return dbExecutor.getCount(Student.tableName);
    }

    public List<Student> getStudentsFromGroup(String groupName){
        String[] tables = new String[2];
        tables[0] = StudentFull.studentsTableName;
        tables[1] = StudentFull.groupTableName;
        String[] columns = new String[3];
        columns[0] = "groupId";
        columns[1] = "id";
        columns[2] = "name";

        ResultSet resultSet = dbExecutor.getStudentsFromGroup(tables, columns, String.format("'%s'",groupName));
        List<Student> students = new ArrayList<>();

        try {
            while (resultSet.next()) {
                students.add (new Student(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4)
                ));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return students;

    }

    public List<Student> getFemales(){

        ResultSet resultSet = dbExecutor.get(Student.tableName,"sex","'Жен'");
        List<Student> students = new ArrayList<>();

        try {
            while (resultSet.next()) {
                students.add(new Student(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4 )
                ));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return students;

    }


}


