package com.github.dao;

import com.github.entity.Event;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Set;

@Repository
public class EventDao extends AbstractDao<Event> {

    public EventDao(EntityManager entityManager) {
        super(entityManager, Event.class);
    }

}
