package tables;

import java.util.List;

public interface ITable<T> {
     void fill();
     List<T> list();
}
