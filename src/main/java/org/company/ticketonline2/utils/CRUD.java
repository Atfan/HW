package org.company.ticketonline2.utils;

import java.util.List;

public interface CRUD <T> {
    void save(T data) ;

    int[] saveToList(List<T> arrData) ;

    void update(T data) ;

    void delete(T data) ;

    List<T> toList() ;

    void deleteAll() ;

    T findById(Long id) ;
}
