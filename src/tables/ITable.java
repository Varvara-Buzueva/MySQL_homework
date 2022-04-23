package tables;

import dbo.Student;

import java.util.List;

public interface ITable<T> {
     void fill();
     List<T> list();
}

/*
     List <T> getAll();
     int getCount();
     List <T> getByCondition(String column, String condition);
     void insert(T newElement);
     void delete(T element);

 */