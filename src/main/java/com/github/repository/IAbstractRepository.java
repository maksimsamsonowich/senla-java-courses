package com.github.repository;

import java.util.List;

public interface IAbstractRepository<T> {

    T create(T entity);

    T read(Long id);

    T update(T entity);

    void delete(T entity);

    List<T> getAll();

}