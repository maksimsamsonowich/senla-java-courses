package com.github;

import com.github.controller.Controller;
import com.github.dao.Database;
import com.github.di.ApplicationInit;
import com.github.di.Context;

public class Application {

    public static void main(String[] args) {

        try {
            Context context = ApplicationInit.init(Controller.class);
            Database object = (Database) context.getObject("dao.database");
            System.out.println(object.getSomething());

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

}
