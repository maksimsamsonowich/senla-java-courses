package com.github.repository.impl;

import com.github.filter.PaginationDto;
import com.github.repository.IAbstractRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Slf4j
@Repository
@AllArgsConstructor
public abstract class AbstractRepository<T> implements IAbstractRepository<T> {

    @PersistenceContext
    protected final EntityManager entityManager;

    protected final CriteriaBuilder criteriaBuilder;

    protected final Class<T> entityClass;

    @Override
    public T create(T entity) {
        entityManager.persist(entity);

        log.info("Repository: " + entityClass.getName() + " created.");

        return entity;
    }

    @Override
    public T readById(Long id) {
        return entityManager.find(entityClass, id);
    }

    @Override
    public T update(T entity) {
        entityManager.merge(entity);

        log.info("Repository: " + entityClass.getName() + " updated.");

        return entity;
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(readById(id));

        log.info("Repository: " + entityClass.getName() + " deleted.");
    }

    @Override
    public List<T> getAll(PaginationDto pagination) {
        CriteriaQuery<T> cq = criteriaBuilder.createQuery(entityClass);
        Root<T> rootEntry = cq.from(entityClass);
        CriteriaQuery<T> all = cq.select(rootEntry);
        TypedQuery<T> allQuery = entityManager.createQuery(all);
        allQuery.setMaxResults(pagination.getPageSize());
        allQuery.setFirstResult(pagination.getPageNumber());

        log.info("Repository: " + entityClass.getName() + " set pulled.");

        return allQuery.getResultList();
    }
}
