package test.java.core.repo;

import java.util.List;

public interface Repository<T> {   
    boolean insert(T object);
    List<T> selectAll();
    boolean update(T object);
    void remove(T object);
    T selectById(int id) ;
}
