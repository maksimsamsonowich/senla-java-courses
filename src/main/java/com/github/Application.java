package com.github;

import com.github.controller.EventController;
import com.github.controller.LocationController;
import com.github.controller.UserController;
import com.github.dao.LocationDao;
import com.github.dto.EventDto;
import com.github.dto.LocationDto;
import com.github.dto.UserDto;

import com.github.mapper.JsonMapper;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan
public class Application {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Application.class);

        String firstLocation = "{\"id\":\"1\",\"title\":\"Heart\",\"address\":\"г.Гродно, ул.Кабяка, д.112\",\"capacity\":\"677\"}";

        JsonMapper jsonMapper = applicationContext.getBean(JsonMapper.class);

        LocationDto firstDto = jsonMapper.toEntity(firstLocation, LocationDto.class);
        LocationController locationController = applicationContext.getBean(LocationController.class);

        locationController.updateLocation(firstDto);

        System.out.println(jsonMapper.toJson(locationController.readLocation(firstDto)));

    }

}
