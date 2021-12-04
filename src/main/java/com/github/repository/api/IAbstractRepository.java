package com.github.repository.api;

public interface IAbstractRepository<T> {

    T create(T entity);

    T read(int id);

    T update(T entity);

    void delete(T entity);

}