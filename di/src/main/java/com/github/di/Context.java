package com.github.di;

import java.util.HashMap;

public class Context {

    HashMap<String, Object> objects = new HashMap<>();

    public <T> T getObject(Class<T> someClass){
        return (T) objects.get(someClass);
    }


}
