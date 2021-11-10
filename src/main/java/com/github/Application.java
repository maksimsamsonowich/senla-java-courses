package com.github;

import com.github.controller.EventController;
import com.github.controller.UserController;
import com.github.dto.EventDto;
import com.github.dto.UserDto;

import com.github.mapper.JsonMapper;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan
public class Application {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Application.class);
        JsonMapper jsonMapper = applicationContext.getBean(JsonMapper.class);

        String firstEntity = "{\"id\":\"1\",\"login\":\"motzisudo\",\"password\":\"motzisudo\",\"email\":\"motzisudo@mail.ru\"}";
        String secondEntity = "{\"id\":\"2\",\"login\":\"ne-motzisudo\",\"password\":\"odusiztom\",\"email\":\"anyonebutnotmotzisudo@mail.ru\"}";

        UserDto firstUser = jsonMapper.toEntity(firstEntity, UserDto.class);
        UserDto secondUser = jsonMapper.toEntity(secondEntity, UserDto.class);

        UserController userController = applicationContext.getBean(UserController.class);

        userController.createUser(firstUser);
        userController.createUser(secondUser);

        System.out.println(jsonMapper.toJson(userController.readUser(firstUser)));
        userController.deleteUser(secondUser);

        firstUser.setEmail("json@mail.ru");
        firstUser.setLogin("json");
        firstUser.setPassword("json");

        firstUser = userController.update(firstUser);
        System.out.println(jsonMapper.toJson(userController.readUser(firstUser)));

        String eventEntity = "{\"id\":\"1\",\"title\":\"Death Race For Love\",\"description\":\"Flaws and sins\",\"ageLimit\":\"18\",\"occupiedPlace\":\"48\"}";
        String secondEventEntity = "{\"id\":\"2\",\"title\":\"YUNG LEAN IN MY HEAD\",\"description\":\"Dark Thoughts\",\"ageLimit\":\"18\",\"occupiedPlace\":\"35\"}";

        EventDto firstEvent = jsonMapper.toEntity(eventEntity, EventDto.class);
        EventDto secondEvent = jsonMapper.toEntity(secondEventEntity, EventDto.class);

        EventController iEventController = applicationContext.getBean(EventController.class);

        iEventController.createEvent(firstEvent);
        iEventController.createEvent(secondEvent);

        System.out.println(jsonMapper.toJson(iEventController.readEvent(firstEvent)));

        iEventController.deleteEvent(secondEvent);

        firstEvent.setAgeLimit(21);
        firstEvent.setDescription("Who Shot Cupid?");
        firstEvent.setTitle("Goodbye & Good Riddance");
        firstEvent.setOccupiedPlace(60);

        firstEvent = iEventController.updateEventDescription(firstEvent);

        System.out.println(jsonMapper.toJson(iEventController.readEvent(firstEvent)));
    }

}
