package com.github.repository.impl;

import com.github.entity.Credential;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;

@Repository
public class CredentialRepository extends AbstractRepository<Credential> {

    public CredentialRepository(EntityManager entityManager, CriteriaBuilder criteriaBuilder) {
        super(entityManager, criteriaBuilder, Credential.class);
    }

    public Credential getCredentialByEmail(String email) {
        String queryString = "select c from Credential c join fetch c.roles r join fetch c.user us where c.email = :email";
        TypedQuery<Credential> query = entityManager.createQuery(queryString, Credential.class);
        query.setParameter("email", email);

        return query.getSingleResult();
    }

}
