package com.github.dao;

import com.github.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public class Database implements IDatabase {

    @Value("param.value")
    private String something;

    private List<User> users;

    @Override
    public String getSomething(){
        return something;
    }

    @Override
    public List<User> getUsers() {
        return users;
    }
}
