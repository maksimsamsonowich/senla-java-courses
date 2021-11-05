package com.github;

import com.github.controller.Controller;

import com.github.dao.UserDao;
import com.github.dto.UserDto;
import com.github.entity.User;
import com.github.mappers.JsonMapper;
import com.github.mappers.UserMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringReader;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

@ComponentScan
public class Application {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(PRIVATE);
        return mapper;
    }

    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Application.class);
        Controller bean = applicationContext.getBean(Controller.class);
        System.out.println(bean.getSomething());


        String forDeserialize = "{\"login\":\"motzisudo\",\"password\":\"motzisudo\",\"email\":\"motzisudo@mail.ru\"}";
        JsonMapper jsonMapper = applicationContext.getBean(JsonMapper.class);

        applicationContext.getBean(UserDao.class).createUser(jsonMapper.toEntity(forDeserialize, User.class));

        System.out.println(applicationContext.getBean(UserDao.class).getEmail("motzisudo"));
    }

}
