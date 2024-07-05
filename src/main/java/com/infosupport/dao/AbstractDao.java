package com.infosupport.dao;

public interface AbstractDao<T> {

    void delete(int id);
    T add(T type);
    T get(int id);
}
