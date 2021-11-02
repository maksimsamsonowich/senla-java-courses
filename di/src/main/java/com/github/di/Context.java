package com.github.di;

import com.github.di.annotations.Autowire;
import com.github.di.annotations.Component;
import com.github.di.annotations.Value;
import com.github.di.exceptions.NewInstanceCreationException;
import com.github.di.exceptions.NoAccessException;
import com.github.di.exceptions.NoSuchFileException;
import com.github.di.exceptions.NoSuchImplementation;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Objects;

public class Context {

    ClassReader classReader;
    HashMap<String, Object> beanContainer = new HashMap<>();

    public Object getBean(String className){
        return beanContainer.get(className);
    }

    public Context(Class<?> applicationClass) throws NoAccessException, NewInstanceCreationException, NoSuchFileException, NoSuchImplementation {
        this.classReader = new ClassReader();
        classReader.findClasses(applicationClass);
        get(applicationClass);
    }

    private void get(Class<?> someClass) throws NoSuchFileException, NoSuchImplementation, NewInstanceCreationException, NoAccessException {

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
                    PropertiesWork propertiesWork = new PropertiesWork();

                    field.setAccessible(true);
                    try {
                        field.set(instance, propertiesWork.getPropertiesValue(valueAnn.value()));
                    } catch (IllegalAccessException ex) {
                        throw new NoAccessException("There is no access to " + field.getName());
                    }
                }

                if (field.isAnnotationPresent(Autowire.class)) {
                    if (field.getType().isInterface()){

                        Object reference;
                        try {
                            reference = classReader.getImplementation(field.getType()).getDeclaredConstructor().newInstance();
                        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException ex) {
                            throw new NewInstanceCreationException("There some error while creation new instance");
                        }

                        field.setAccessible(true);

                        try {
                            field.set(instance, reference);
                        } catch (IllegalAccessException ex) {
                            throw new NoAccessException("There is no access to " + field.getName());
                        }

                        beanContainer.put(reference.getClass().getName(), reference);
                        get(reference.getClass());
                    }
                }
            }

            beanContainer.put(instance.getClass().getName(), instance);
        }

    }

    private Object getInstance(Class<?> someClass) throws NoSuchImplementation, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (beanContainer.containsKey(someClass.getName()))
            return beanContainer.get(someClass.getName());
        else if (someClass.isInterface())
            return Objects.requireNonNull(classReader.getImplementation(someClass).getDeclaredConstructor().newInstance());
        else
            return someClass.getDeclaredConstructor().newInstance();
    }
}
