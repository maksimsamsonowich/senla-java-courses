package com.github.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@AllArgsConstructor
public abstract class AbstractDao<T> implements IAbstractDao<T> {

    @PersistenceContext
    private final EntityManager entityManager;

    private final Class<T> entityClass;

    @Override
    public T create(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public T read(int id) {
        return entityManager.find(entityClass, id);
    }

    @Override
    public T update(T entity) {
        entityManager.merge(entity);
        return entity;
    }

    @Override
    public void delete(T entity) {
        entityManager.remove(entity);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

}
