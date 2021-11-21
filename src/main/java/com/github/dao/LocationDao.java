package com.github.dao;

import com.github.entity.Location;
import com.github.exceptions.location.NoSuchLocationException;
import com.github.metamodels.Location_;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Objects;


@Repository
public class LocationDao {

    @Value("${database.access.error.message}")
    private String ERROR_MESSAGE;

    private final CriteriaBuilder criteriaBuilder;
    private EntityManager entityManager;

    public LocationDao(LocalContainerEntityManagerFactoryBean entityManager) {
        criteriaBuilder = entityManager.getObject().getCriteriaBuilder();
        this.entityManager = entityManager.getNativeEntityManagerFactory().createEntityManager();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Location update(Location location) {
        entityManager.getTransaction().begin();
        if (Objects.nonNull(entityManager.find(Location.class, location.getId()))){
            entityManager.merge(location);
        }
        entityManager.getTransaction().commit();
        return location;
    }

    @Transactional
    public Location read(Location location) {
        CriteriaQuery<Location> criteriaQuery = criteriaBuilder.createQuery(Location.class);
        Root<Location> locationRoot = criteriaQuery.from(Location.class);
        criteriaQuery.select(locationRoot)
                .where(criteriaBuilder.equal(locationRoot.get(Location_.ID), location.getId()));;
        return entityManager.createQuery(criteriaQuery)
                .getResultList().stream()
                .findFirst()
                .orElseThrow(() -> new NoSuchLocationException(ERROR_MESSAGE));
    }

}
