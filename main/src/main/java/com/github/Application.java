package com.github;

import com.github.controller.Controller;
import com.github.controller.IController;
import com.github.di.ApplicationInit;
import com.github.di.Context;

public class Application {

    public static void main(String[] args) {

        try {
            Context context = ApplicationInit.init(IController.class);
            IController object = (Controller) context.getObject(Controller.class.getName());
            System.out.println(object.getSomething());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

}
