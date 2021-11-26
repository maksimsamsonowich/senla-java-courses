package com.github.dao.api;

public interface IAbstractDao<T> {

    T create(T entity);

    T read(int id);

    T update(T entity);

    void delete(T entity);

}