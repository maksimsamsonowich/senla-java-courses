package com.github.dao;

import com.github.entity.Event;
import com.github.entity.Location;

import com.github.metamodels.Event_;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class LocationDao extends AbstractDao<Location>  {

    @Value("${database.access.error.message}")
    private String ERROR_MESSAGE;

    private final CriteriaBuilder criteriaBuilder;

    public LocationDao(EntityManager entityManager) {
        super(entityManager, Location.class);
        criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Location getLocationByEvent(Event event) {
        EntityGraph entityGraph = getEntityManager().getEntityGraph("event-entity-graph");

        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Event> criteriaQuery = criteriaBuilder.createQuery(Event.class);

        Root<Event> root = criteriaQuery.from(Event.class);

        criteriaQuery.where(criteriaBuilder.equal(root.get(Event_.ID), event.getId()));

        TypedQuery<Event> typedQuery = getEntityManager().createQuery(criteriaQuery);
        typedQuery.setHint("javax.persistence.fetchgraph", entityGraph);

        return typedQuery.getSingleResult().getLocation();
    }

}
