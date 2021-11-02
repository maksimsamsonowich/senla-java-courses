package com.github.di;

import com.github.di.exceptions.NoSuchFileException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public class PropertiesScanner {

    private final String propertiesPath = "application.properties";
    private final Properties properties;

    public PropertiesScanner() {
        properties = new Properties();
        getReader();
    }

    protected <T> T getPropertiesValue(String annSignature) {
        Object value = properties.getProperty(annSignature);

        if (Objects.nonNull(value))
            return (T) value;
        else
            throw new NoSuchFileException("There is no such properties");
    }

    private void getReader() {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(propertiesPath)) {
            this.properties.load(is);
        } catch (IOException ex) {
            throw new NoSuchFileException("Cannot find the file specified", ex);
        }
    }

}
