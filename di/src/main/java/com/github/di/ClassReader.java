package com.github.di;

import com.github.di.exceptions.NoAccessException;
import com.github.di.exceptions.NoSuchFileException;
import com.github.di.exceptions.NoSuchImplementation;

import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ClassReader {

    private final String CLASS_EXTENSION = ".class";
    private final Set<Class<?>> foundClasses;

    public ClassReader() {
        this.foundClasses = new HashSet<>();
    }

    public Set<Class<?>> findClasses(Class<?> startClass) throws NoSuchFileException, NoAccessException {
        String directory = getDirectory(startClass);
        File file = new File(directory);

        if(!file.isDirectory()) {
            throw new NoSuchFileException("There is no such file like " + directory);
        }

        try {
            for (File someFile : Objects.requireNonNull(file.listFiles())) {
                scanDirectory(someFile, "");
            }
        } catch (ClassNotFoundException e) {
            throw new NoAccessException("There no access to class");
        }

        return foundClasses;
    }

    private String getDirectory(Class<?> someClass) {
        return someClass.getProtectionDomain().getCodeSource().getLocation().getFile();
    }

    private void scanDirectory(File file, String path) throws ClassNotFoundException {
        if (file.isDirectory()) {
            path += file.getName() + ".";
            for (File innerFile : Objects.requireNonNull(file.listFiles())) {
                scanDirectory(innerFile, path);
            }
        }
        if (file.getName().endsWith(CLASS_EXTENSION)) {
            String className = path + file.getName().replaceAll(CLASS_EXTENSION, "");
            Class<?> clazz = Class.forName(className);
            if (!clazz.isInterface()) {
                foundClasses.add(Class.forName(className));
            }
        }
    }

    public Class<?> getImplementation(Class<?> someClass) throws NoSuchImplementation {
        for (Class<?> clazz : foundClasses) {
            if (someClass.isAssignableFrom(clazz)) {
                return clazz;
            }
        }

        throw new NoSuchImplementation("There is no implementation for " + someClass.getName() + "interface");
    }
}
