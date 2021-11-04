package com.github.controller;

import com.github.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Controller implements IController {

    private final IService iService;

    public Controller(IService iService) {
        this.iService = iService;
    }

    @Override
    public String getSomething() {
        return iService.getSomething();
    }
}
