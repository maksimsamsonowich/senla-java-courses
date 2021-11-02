package com.github.dao;

import com.github.di.annotations.Component;
import com.github.di.annotations.Value;

@Component
public class Database implements IDatabase {

    @Value("param.value")
    String something;

    @Override
    public String getSomething(){
        return something;
    }
}
