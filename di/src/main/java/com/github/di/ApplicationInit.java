package com.github.di;

import com.github.di.annotations.Component;

public class ApplicationInit {

    public static Context init(Class<?> applicationClass) throws InstantiationException, IllegalAccessException {
        Context contextToReturn = new Context();

        if (applicationClass.isAnnotationPresent(Component.class)) {
            Component comp = applicationClass.getAnnotation(Component.class);
            contextToReturn.objects.put(comp.name(), applicationClass.newInstance());
        }

        return contextToReturn;
    }

}
