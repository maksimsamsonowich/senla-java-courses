package com.github.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public abstract class AbstractDao<T> implements IAbstractDao<T> {

    @Override
    public void create(T someObject) {

    }

    @Override
    public T read(T someObject) {
        return someObject;
    }

    @Override
    public T update(T someObject) {
        return someObject;
    }

    @Override
    public void delete(T someObject) {

    }

}
