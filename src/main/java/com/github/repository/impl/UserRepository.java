package com.github.repository.impl;

import com.github.entity.User;
import com.github.repository.impl.AbstractRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;

@Repository
public class UserRepository extends AbstractRepository<User> {

    public UserRepository(EntityManager entityManager, CriteriaBuilder criteriaBuilder) {
        super(entityManager, criteriaBuilder, User.class);
    }

    public User findByUsername(String username) {
        return (User) entityManager.createQuery(
                String.format("select u from User u where u.login = %s", username))
                .getSingleResult();
    }

}
