package com.github.di;


public class ApplicationInit {

    public static Context init(Class<?> applicationClass) {
        return new Context(applicationClass);
    }
}
