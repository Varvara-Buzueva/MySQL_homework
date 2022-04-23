package utils.resources;

import dbo.Student;

public class ReadStudentsTable extends ReadTableData<Student>{
    @Override
    protected Student getElement(String[] data) {
        return new Student(new Integer(data[0]), data[1], data[2],new Integer(data[3]));
    }
}
