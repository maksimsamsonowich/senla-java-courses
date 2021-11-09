package com.github.dao;

public interface IDao<T> {

    void create(T object);

    T read(T object);

    T update(T object, String value);

    void delete(T object);


}