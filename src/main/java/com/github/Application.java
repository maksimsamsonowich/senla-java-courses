package com.github;

import com.github.controller.EventController;
import com.github.controller.LocationController;
import com.github.controller.UserController;
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
        JsonMapper jsonMapper = applicationContext.getBean(JsonMapper.class);

        String firstLocation = "{\"id\":\"1\",\"title\":\"Heart\",\"address\":\"г.Гродно, ул.Кабяка, д.112\",\"capacity\":\"60\"}";
        String secondLocation = "{\"id\":\"2\",\"title\":\"Art House\",\"address\":\"г.Гродно, ул.Ожешко, д.9\",\"capacity\":\"45\"}";

        LocationDto firstDto = jsonMapper.toEntity(firstLocation, LocationDto.class);
        LocationDto secondDto = jsonMapper.toEntity(secondLocation, LocationDto.class);

        LocationController locationController = applicationContext.getBean(LocationController.class);
        locationController.createLocation(secondDto);

        System.out.println(jsonMapper.toJson(locationController.readLocation(firstDto)));
        System.out.println(jsonMapper.toJson(locationController.readLocation(secondDto)));

        locationController.deleteLocation(secondDto);

        firstDto.setCapacity(66);

        System.out.println(jsonMapper.toJson(locationController.updateLocation(firstDto)));
    }

}
