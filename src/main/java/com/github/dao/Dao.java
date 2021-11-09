package com.github.dao;

import com.github.exceptions.user.NoSuchUserException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public abstract class Dao<T> implements IDao<T> {

    private final Set<T> someData;

    public Dao(Set<T> someData) {
        this.someData = someData;
    }

    @Override
    public void create(T someObject) {
        someData.add(someObject);
    }

    @Override
    public T read(T someObject) {
        return getEntity(someObject);
    }

    public T update(T someObject) {
        return getEntity(someObject);
    }

    @Override
    public void delete(T someObject) {
        someData.remove(getEntity(someObject));
    }

    private T getEntity(T obj) {
        return someData.stream()
                .filter(object -> object.equals(obj))
                .findFirst()
                .orElseThrow(() -> new NoSuchUserException("There is no such user;"));
    }

}
