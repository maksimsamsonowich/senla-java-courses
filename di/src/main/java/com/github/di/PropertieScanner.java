package com.github.di;

import com.github.di.exceptions.NoSuchFileException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class PropertieScanner {

    private final String propertiesPath = "main/src/main/resources/application.properties";

    protected <T> T getPropertiesValue(String annSignature) {
        Properties property = new Properties();
        try {
            property.load(new FileInputStream(propertiesPath));
        } catch (IOException ex) {
            throw new NoSuchFileException("Cannot find the file specified", ex);
        }

        Object value = property.getProperty(annSignature);

        if (!Objects.isNull(value))
            return (T) value;
        else
            throw new NoSuchFileException("There is no such properties");
    }

}
