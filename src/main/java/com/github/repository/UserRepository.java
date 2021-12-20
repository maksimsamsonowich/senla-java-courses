package com.github.repository;

import com.github.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    @EntityGraph(value = "user-entity-graph", type = EntityGraph.EntityGraphType.LOAD)
    User findByCredential_Email(String email);

}
