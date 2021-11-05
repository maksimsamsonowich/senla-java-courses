package com.github.mappers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.entity.User;
import com.github.exceptions.NoSuchEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringReader;
import java.util.Objects;

@Component
public class JsonMapper {

    @Autowired
    private ObjectMapper objectMapper;

    public <T> T toEntity(String jsonString, Class<?> someClass) {
        StringReader reader = new StringReader(jsonString);
        try {
            return Objects.isNull(someClass) ? null : (T) objectMapper.readValue(reader, someClass);
        } catch (IOException ex) {
            throw new NoSuchEntityException("There is no entity with such constructor");
        }
    }
}
