package com.github.dao;

import com.github.entity.Location;

import com.github.exceptions.location.NoSuchLocationException;
import com.github.metamodels.Location_;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


@Repository
public class LocationDao extends AbstractDao<Location>  {

    @Value("${database.access.error.message}")
    private String ERROR_MESSAGE;

    private final CriteriaBuilder criteriaBuilder;

    public LocationDao(EntityManager entityManager) {
        super(entityManager, Location.class);
        criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Location nothing(Location location) {
        CriteriaQuery<Location> criteriaQuery = criteriaBuilder.createQuery(Location.class);
        Root<Location> locationRoot = criteriaQuery.from(Location.class);
        criteriaQuery.select(locationRoot);

        TypedQuery<Location> query = getEntityManager().createQuery(criteriaQuery);

        List<Location> locationList = query.getResultList();

        return getEntityManager().createQuery(criteriaQuery)
                .getResultList().stream()
                .findFirst()
                .orElseThrow(() -> new NoSuchLocationException(ERROR_MESSAGE));
    }

}
