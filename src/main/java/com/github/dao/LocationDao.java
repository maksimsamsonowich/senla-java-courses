package com.github.dao;

import com.github.entity.Event;
import com.github.entity.Event_;
import com.github.entity.Location;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class LocationDao extends AbstractDao<Location>  {

    public LocationDao(EntityManager entityManager, CriteriaBuilder criteriaBuilder) {
        super(entityManager, criteriaBuilder, Location.class);
    }

    public Location getLocationByEvent(Event event) {
        EntityGraph<?> entityGraph = entityManager.getEntityGraph("event-entity-graph");

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Event> criteriaQuery = criteriaBuilder.createQuery(Event.class);

        Root<Event> root = criteriaQuery.from(Event.class);

        criteriaQuery.where(criteriaBuilder.equal(root.get(Event_.ID), event.getId()));

        TypedQuery<Event> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setHint("javax.persistence.fetchgraph", entityGraph);

        return typedQuery.getSingleResult().getLocation();
    }

}
