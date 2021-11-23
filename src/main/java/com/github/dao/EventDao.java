package com.github.dao;

import com.github.entity.Event;

import com.github.entity.Location;
import com.github.entity.Location_;

import org.springframework.beans.factory.annotation.Value;

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

    @Value("${event.error.message}")
    private String ERROR_MESSAGE;

    private CriteriaBuilder criteriaBuilder;

    public EventDao(EntityManager entityManager) {
        super(entityManager, Event.class);
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Set<Event> getSubEntityByEntity(Location location) {
        EntityGraph entityGraph = getEntityManager().getEntityGraph("location-entity-graph");

        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Location> criteriaQuery = criteriaBuilder.createQuery(Location.class);

        Root<Location> root = criteriaQuery.from(Location.class);

        criteriaQuery.where(criteriaBuilder.equal(root.get(Location_.ID), location.getId()));

        TypedQuery<Location> typedQuery = getEntityManager().createQuery(criteriaQuery);
        typedQuery.setHint("javax.persistence.fetchgraph", entityGraph);

        return typedQuery.getSingleResult().getEvents();
    }


}
