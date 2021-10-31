package com.github.di;

import com.github.di.annotations.Autowire;
import com.github.di.annotations.Component;
import com.github.di.annotations.Value;
import org.reflections.Reflections;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

public class ApplicationInit {

    public static Context init(Class<?> applicationClass) throws InstantiationException, IllegalAccessException, IOException {
        Context contextToReturn = new Context();

        contextToReturn.objects = getObjects(contextToReturn.objects, applicationClass);

        return contextToReturn;
    }


    private static HashMap<String, Object> getObjects(HashMap<String, Object> container, Class<?> someClass) throws IOException, IllegalAccessException,
            InstantiationException {

        if (someClass.isAnnotationPresent(Component.class)) {
            Component comp = someClass.getAnnotation(Component.class);

            Object someNewInstance = someClass.newInstance();

            for (Field field : someClass.getDeclaredFields()){

                if (field.isAnnotationPresent(Value.class)) {
                    Properties property = new Properties();
                    property.load(new FileInputStream("main/src/main/resources/application.properties"));

                    field.setAccessible(true);
                    Value value = field.getAnnotation(Value.class);
                    field.set(someNewInstance, property.getProperty(value.value()));
                }

                if (field.isAnnotationPresent(Autowire.class)) {
                    Autowire autowire = field.getAnnotation(Autowire.class);

                    if (field.getType().isInterface()){

                        for (Class<?> clazz : new Reflections("com.github").getSubTypesOf(field.getType())) {
                            if (clazz.isInterface())
                                continue;

                            Object reference = clazz.newInstance();

                            field.setAccessible(true);
                            field.set(someNewInstance, reference);
                            container.put(autowire.name(), reference);
                            getObjects(container, clazz);
                        }
                    }
                }
            }

            container.put(comp.name(), someNewInstance);
        }
        return container;
    }


}
