package com.github.di;

import com.github.di.exceptions.NoSuchFileException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public class PropertiesScanner {

    private final String propertiesPath = "application.properties";

    protected <T> T getPropertiesValue(String annSignature) {
        InputStream is = getClass().getClassLoader().getResourceAsStream("application.properties");
        Properties property = new Properties();
        try {
            property.load(is);
        } catch (IOException ex) {
            throw new NoSuchFileException("Cannot find the file specified", ex);
        }

        Object value = property.getProperty(annSignature);

        if (Objects.nonNull(value))
            return (T) value;
        else
            throw new NoSuchFileException("There is no such properties");
    }

}
