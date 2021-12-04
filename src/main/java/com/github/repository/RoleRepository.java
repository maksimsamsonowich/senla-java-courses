package com.github.repository;

import com.github.entity.Role;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;

public class RoleRepository extends AbstractRepository<Role> {

    public RoleRepository(EntityManager entityManager, CriteriaBuilder criteriaBuilder, Class<Role> entityClass) {
        super(entityManager, criteriaBuilder, entityClass);
    }

    public Role findByName(String name) {
        return (Role) entityManager.createQuery(
                        String.format("select r from Role r where r.name = %s", name))
                        .getSingleResult();
    }

}
