package com.github.dao;

public interface IAbstractDao<T> {

    void create(T object);

    T read(T object);

    T update(T object);

    void delete(T object);

}