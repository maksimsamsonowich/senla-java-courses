package com.github.di;

import java.util.HashMap;

public class Context {

    HashMap<String, Object> objects = new HashMap<>();

    public Object getObject(String className){
        return objects.get(className);
    }


}