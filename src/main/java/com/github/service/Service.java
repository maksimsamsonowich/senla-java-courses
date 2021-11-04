package com.github.service;

import com.github.dao.IDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Service implements IService {

    private final IDatabase iDatabase;

    public Service(IDatabase iDatabase) {
        this.iDatabase = iDatabase;
    }

    @Override
    public String getSomething(){
        return iDatabase.getSomething();
    }
}