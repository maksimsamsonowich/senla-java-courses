package com.github;

import com.github.controller.Controller;
import com.github.controller.IController;
import com.github.di.ApplicationInit;
import com.github.di.Context;
import com.github.di.exceptions.NewInstanceCreationException;

import com.github.di.exceptions.NoSuchImplementation;

import java.io.IOException;

public class Application {

    public static void main(String[] args) {

        Context context = ApplicationInit.init(IController.class);
        IController object = (Controller) context.getBean(Controller.class.getName());
        System.out.println(object.getSomething());

    }

}
