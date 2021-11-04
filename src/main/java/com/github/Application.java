package com.github;

import com.github.controller.Controller;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class Application {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Application.class);
        Controller bean = applicationContext.getBean(Controller.class);
        System.out.println(bean.getSomething());

    }

}
