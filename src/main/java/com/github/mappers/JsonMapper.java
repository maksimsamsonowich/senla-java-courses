package com.github.mappers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.exceptions.NoSuchEntityException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Objects;

@Component
public class JsonMapper {

    private final ObjectMapper objectMapper;

    public JsonMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public <T> T toEntity(String jsonString, Class<?> someClass) {
        StringReader reader = new StringReader(jsonString);
        try {
            return Objects.isNull(someClass) ? null : (T) objectMapper.readValue(reader, someClass);
        } catch (IOException ex) {
            throw new NoSuchEntityException("There is no entity with such constructor;");
        }
    }
    
    public String toJson(Object someClass) {
        StringWriter writer = new StringWriter();
        try {
            objectMapper.writeValue(writer, someClass);
            return writer.toString();
        } catch (IOException exception) {
            throw new NoSuchEntityException("Input/Output error;");
        }
    }
    
}
