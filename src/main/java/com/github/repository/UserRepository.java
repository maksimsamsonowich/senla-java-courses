package com.github.repository;

import com.github.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByCredential_Email(String email);

}
