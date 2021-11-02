package com.github;

import com.github.controller.Controller;
import com.github.controller.IController;
import com.github.di.ApplicationInit;
import com.github.di.ClassReader;
import com.github.di.Context;
import com.github.di.exceptions.NewInstanceCreationException;
import com.github.di.exceptions.NoAccessException;
import com.github.di.exceptions.NoSuchFileException;
import com.github.di.exceptions.NoSuchImplementation;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Application {

    public static void main(String[] args) throws IOException, IllegalAccessException, NewInstanceCreationException, NoSuchImplementation {

        Context context = ApplicationInit.init(IController.class);
        IController object = (Controller) context.getBean(Controller.class.getName());
        System.out.println(object.getSomething());

    }

}
