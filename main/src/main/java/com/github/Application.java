package com.github;

import com.github.controller.Controller;
import com.github.controller.IController;
import com.github.dao.Database;
import com.github.di.ApplicationInit;
import com.github.di.Context;

public class Application {

    public static void main(String[] args) {


        try {
            Context context = ApplicationInit.init(Controller.class);
            IController object = (Controller) context.getObject("controller.controller");
            System.out.println(object.getSomething());

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

}
