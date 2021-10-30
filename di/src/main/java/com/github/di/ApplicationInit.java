package com.github.di;

import com.github.di.annotations.Autowire;
import com.github.di.annotations.Component;
import com.github.di.annotations.Value;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Properties;

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
            Object someNewInstance = (Object) someClass.newInstance();

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
                    container.put(autowire.name(), (Object)field.getType().newInstance());
                    container = getObjects(container, field.getType());
                }
            }

            container.put(comp.name(), someNewInstance);

        }
        return container;
    }

}
