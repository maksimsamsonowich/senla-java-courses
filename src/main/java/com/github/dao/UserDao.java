package com.github.dao;

import com.github.entity.Ticket;
import com.github.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Set;

@Repository
public class UserDao extends AbstractDao<User>  {

    public UserDao(EntityManager entityManager, CriteriaBuilder criteriaBuilder) {
        super(entityManager, criteriaBuilder, User.class);
    }

}
