package com.github.dao.api;

import java.util.List;

public interface IAbstractDao<T> {

    T create(T entity);

    T read(int id);

    T update(T entity);

    void delete(T entity);

    List<T> getAll();

}