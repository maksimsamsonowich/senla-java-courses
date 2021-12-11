package com.github.repository;

public interface IAbstractRepository<T> {

    T create(T entity);

    T read(Long id);

    T update(T entity);

    void delete(T entity);

}