package com.github.repository;

import com.github.entity.Credential;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;

@Repository
public class CredentialRepository extends AbstractRepository<Credential> {

    public CredentialRepository(EntityManager entityManager, CriteriaBuilder criteriaBuilder,
                                Class<Credential> entityClass) {
        super(entityManager, criteriaBuilder, entityClass);
    }

    public Credential getCredentialByEmail(String email) {
        return (Credential) entityManager.createQuery(
                        String.format("select u from Credential u where u.email = %s", email))
                        .getSingleResult();
    }

}
