package com.github.di;

import com.github.di.annotations.Autowire;
import com.github.di.annotations.Component;
import com.github.di.annotations.Value;
import org.reflections.Reflections;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class ApplicationInit {

    public static Context init(Class<?> applicationClass) throws InstantiationException, IllegalAccessException, IOException,
            NoSuchMethodException, InvocationTargetException {
        Context context = new Context();

        getObjects(context.objects, applicationClass);

        return context;
    }

    private static Object getRealization(Class<?> someClass) throws NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException {

        for (Class<?> clazz : new Reflections("com.github").getSubTypesOf(someClass)) {

            return clazz.getDeclaredConstructor().newInstance();
        }

        return null;
    }

    private static HashMap<String, Object> getObjects(HashMap<String, Object> container, Class<?> someClass) throws IOException, IllegalAccessException,
            InstantiationException, NoSuchMethodException, InvocationTargetException {

        Object someInstance;
        if (container.containsKey(someClass.getName()))
            someInstance = container.get(someClass.getName());
        else if (someClass.isInterface())
            someInstance = getRealization(someClass);
        else
            someInstance = someClass.getDeclaredConstructor().newInstance();

        if (someInstance.getClass().isAnnotationPresent(Component.class)) {

            for (Field field : someInstance.getClass().getDeclaredFields()){

                if (field.isAnnotationPresent(Value.class)) {
                    Value value = field.getAnnotation(Value.class);

                    field.setAccessible(true);
                    field.set(someInstance, getPropertiesValue(value.value()));
                }

                if (field.isAnnotationPresent(Autowire.class)) {

                    if (field.getType().isInterface()){
                        Object reference = getRealization(field.getType());

                        field.setAccessible(true);
                        field.set(someInstance, reference);

                        container.put(reference.getClass().getName(), reference);
                        getObjects(container, reference.getClass());
                    }
                }
            }

            container.put(someInstance.getClass().getName(), someInstance);
        }

        return container;
    }

    private static String getPropertiesValue(String signature) throws IOException {
        Properties property = new Properties();
        property.load(new FileInputStream("main/src/main/resources/application.properties"));

        return property.getProperty(signature);
    }

}
