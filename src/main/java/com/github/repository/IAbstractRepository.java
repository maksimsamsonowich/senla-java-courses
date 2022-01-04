package com.github.repository;

import com.github.filter.PaginationDto;

import java.util.List;

public interface IAbstractRepository<T> {

    T create(T entity);

    T readById(Long id);

    T update(T entity);

    void deleteById(Long id);

    List<T> getAll(PaginationDto pagination);

}