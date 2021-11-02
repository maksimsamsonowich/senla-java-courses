package com.github.di;

import com.github.di.annotations.Autowire;
import com.github.di.annotations.Component;
import com.github.di.annotations.Value;
import com.github.di.exceptions.NewInstanceCreationException;
import com.github.di.exceptions.NoAccessException;
import com.github.di.exceptions.NoSuchFileException;
import com.github.di.exceptions.NoSuchImplementation;
import org.reflections.Reflections;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class ApplicationInit {

    static HashMap<String, Object> tempContainer = new HashMap<>();

    public static Context init(Class<?> applicationClass) throws NoAccessException, NewInstanceCreationException, NoSuchFileException, NoSuchImplementation {
        get(applicationClass);
        return new Context(tempContainer);
    }

    public static void get(Class<?> someClass) throws NoSuchFileException, NoSuchImplementation, NewInstanceCreationException, NoAccessException {

        Object instance;

        try {
            instance = getInstance(someClass);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException ex) {
            throw new NewInstanceCreationException("There some error while creation new instance");
        }

        if (instance.getClass().isAnnotationPresent(Component.class)) {

            for (Field field : instance.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(Value.class)) {
                    Value valueAnn = field.getAnnotation(Value.class);

                    field.setAccessible(true);
                    try {
                        field.set(instance, getPropertiesValue(valueAnn.value()));
                    } catch (IllegalAccessException ex) {
                        throw new NoAccessException("There is no access to " + field.getName());
                    }
                }

                if (field.isAnnotationPresent(Autowire.class)) {
                    if (field.getType().isInterface()){

                        Object reference;
                        try {
                            reference = getImplementation(field.getType()).getDeclaredConstructor().newInstance();
                        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException ex) {
                            throw new NewInstanceCreationException("There some error while creation new instance");
                        }

                        field.setAccessible(true);

                        try {
                            field.set(instance, reference);
                        } catch (IllegalAccessException ex) {
                            throw new NoAccessException("There is no access to " + field.getName());
                        }

                        tempContainer.put(reference.getClass().getName(), reference);
                        get(reference.getClass());
                    }
                }
            }

            tempContainer.put(instance.getClass().getName(), instance);
        }

    }

    private static Object getInstance(Class<?> someClass) throws NoSuchImplementation, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (tempContainer.containsKey(someClass.getName()))
            return tempContainer.get(someClass.getName());
        else if (someClass.isInterface())
            return Objects.requireNonNull(getImplementation(someClass).getDeclaredConstructor().newInstance());
        else
            return someClass.getDeclaredConstructor().newInstance();
    }

    private static Class<?> getImplementation(Class<?> someClass) throws NoSuchImplementation {
        for (Class<?> someClazz : new Reflections("com.github").getSubTypesOf(someClass))
            return someClazz;

        throw new NoSuchImplementation("There is no implementations for " + someClass.getName() + " interface");
    }

    private static String getPropertiesValue(String annSignature) throws NoSuchFileException {
        Properties property = new Properties();
        try {
            property.load(new FileInputStream("main/src/main/resources/application.properties"));
        } catch (IOException ex) {
            throw new NoSuchFileException("Cannot find the file specified", ex);
        }

        return property.getProperty(annSignature);
    }

}
