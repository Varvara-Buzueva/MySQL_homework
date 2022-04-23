package utils.resources;

import java.util.List;

public interface IReadList<T> {
    List<T> read(String filename);
}
