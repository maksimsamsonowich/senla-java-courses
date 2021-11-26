package com.github.dao;

import com.github.dao.api.IAbstractDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;

@Repository
@AllArgsConstructor
public abstract class AbstractDao<T> implements IAbstractDao<T> {

    @PersistenceContext
    protected final EntityManager entityManager;

    protected final CriteriaBuilder criteriaBuilder;

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

}
