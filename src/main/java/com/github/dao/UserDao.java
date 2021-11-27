package com.github.dao;

import com.github.entity.Event;
import com.github.entity.Event_;
import com.github.entity.User;
import com.github.entity.User_;
import com.github.exceptions.user.WrongPasswordException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class UserDao extends AbstractDao<User>  {

    public UserDao(EntityManager entityManager, CriteriaBuilder criteriaBuilder) {
        super(entityManager, criteriaBuilder, User.class);
    }

    public User getAccessToAccount(String username, String password) {
        EntityGraph<?> entityGraph = entityManager.getEntityGraph("user-entity-graph");

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

        Root<User> root = criteriaQuery.from(User.class);

        criteriaQuery.where(criteriaBuilder.equal(root.get(User_.LOGIN), username));

        TypedQuery<User> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setHint("javax.persistence.fetchgraph", entityGraph);

        User user = typedQuery.getSingleResult();

        if (user.getPassword() == password)
            return user;
        else
            throw new WrongPasswordException("Wrong password");
    }

}
