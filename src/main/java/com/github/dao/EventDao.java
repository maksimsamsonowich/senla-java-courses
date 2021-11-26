package com.github.dao;

import com.github.entity.Event;

import com.github.entity.Location;
import com.github.entity.Location_;


import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.Set;

@Repository
public class EventDao extends AbstractDao<Event> {

    public EventDao(EntityManager entityManager, CriteriaBuilder criteriaBuilder) {
        super(entityManager, criteriaBuilder, Event.class);
    }

    public Set<Event> getEventsByLocation(Integer id) {
        EntityGraph<?> entityGraph = entityManager.getEntityGraph("location-entity-graph");

        CriteriaQuery<Location> criteriaQuery = criteriaBuilder.createQuery(Location.class);

        Root<Location> root = criteriaQuery.from(Location.class);

        criteriaQuery.where(criteriaBuilder.equal(root.get(Location_.ID), id));

        TypedQuery<Location> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setHint("javax.persistence.fetchgraph", entityGraph);

        return typedQuery.getSingleResult().getEvents();
    }


}
