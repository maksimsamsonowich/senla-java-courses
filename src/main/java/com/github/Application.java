package com.github;

import com.github.controller.Controller;

import com.github.dto.UserDto;
import com.github.entity.User;
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
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(PRIVATE);
        return mapper;
    }

    public static void main(String[] args) throws IOException {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Application.class);
        Controller bean = applicationContext.getBean(Controller.class);
        System.out.println(bean.getSomething());

        ObjectMapper mapper = new ObjectMapper();
        String forDeserialize = "{\"login\":\"motzisudo\",\"password\":\"motzisudo\",\"email\":\"motzisudo@mail.ru\"}";
        StringReader reader = new StringReader(forDeserialize);
        User user = mapper.readValue(reader, User.class);

        UserDto dto = new UserDto();

        UserMapper userMapper = applicationContext.getBean(UserMapper.class);

        dto = userMapper.toDto(user);

        System.out.println(reader);
    }

}
