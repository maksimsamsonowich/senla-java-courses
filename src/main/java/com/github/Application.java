package com.github;

import com.github.controller.UserController;
import com.github.dto.UserDto;
import com.github.entity.User;
import com.github.mappers.JsonMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import com.fasterxml.jackson.databind.ObjectMapper;

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
        JsonMapper jsonMapper = applicationContext.getBean(JsonMapper.class);

        String firstEntity = "{\"id\":\"1\",\"login\":\"motzisudo\",\"password\":\"motzisudo\",\"email\":\"motzisudo@mail.ru\"}";
        String secondEntity = "{\"id\":\"2\",\"login\":\"ne-motzisudo\",\"password\":\"odusiztom\",\"email\":\"enyonebutnotmotzisudo@mail.ru\"}";

        UserController userController = applicationContext.getBean(UserController.class);

        userController.createUser(firstEntity);
        userController.createUser(secondEntity);

        UserDto dto = userController.updateUserEmail(firstEntity, "nononono@mail.ru");

        System.out.println(userController.readUser(firstEntity));

    }

}
