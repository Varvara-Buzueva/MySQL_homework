package tables;

import dbo.Student;
import utils.resources.ReadStudentsTable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentsTable extends TableAbs implements ITable<Student>{

    private Student student;

    public StudentsTable(String dbType) {
        super(dbType);
         this.dbExecutor.create( Student.tableName,"id int NOT NULL, fio VARCHAR(50) NOT NULL, sex VARCHAR(3) NOT NULL, groupId int NOT NULL, PRIMARY KEY (`id`), FOREIGN KEY (`groupId`) REFERENCES `groups_name` (`id`)");
    }

    public void fill() {
        ReadStudentsTable reader = new ReadStudentsTable();
        List<Student> data = reader.read(Student.fileName);

        for(Student student: data){
            this.dbExecutor.add(Student.tableName, String.format("%s, '%s', '%s', %s", student.getId(), student.getFio(), student.getSex(), student.getGroupId()));
        }
    }

    @Override
    public List<Student> list() {

        ResultSet resultSet = this.dbExecutor.get(Student.tableName);
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


