package com.github.dao;

import com.github.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Set;

@Repository
public class UserDao extends AbstractDao<User>  {

    public UserDao(EntityManager entityManager) {
        super(entityManager, User.class);
    }
}
