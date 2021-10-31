package com.github.controller;

import com.github.di.annotations.Autowire;
import com.github.di.annotations.Component;
import com.github.service.IService;

@Component(name = "controller.controller")
public class Controller implements IController {

    @Autowire(name = "iservice")
    private IService iService;

    @Override
    public String getSomething() {
        return iService.getSomething();
    }

    public Controller(){}
}
