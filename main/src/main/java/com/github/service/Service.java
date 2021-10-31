package com.github.service;

import com.github.dao.IDatabase;
import com.github.di.annotations.Autowire;
import com.github.di.annotations.Component;

@Component(name = "service.service")
public class Service implements IService {

    @Autowire(name = "idatabase")
    private IDatabase iDatabase;

    @Override
    public String getSomething(){
        return iDatabase.getSomething();
    }

    public Service() {}
}