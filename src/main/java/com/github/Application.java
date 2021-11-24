package com.github;

import com.github.controller.EventController;
import com.github.controller.TicketController;
import com.github.dto.EventDto;
import com.github.dto.LocationDto;

import com.github.dto.TicketDto;
import com.github.dto.UserDto;
import com.github.entity.Event;
import com.github.entity.Ticket;
import com.github.mapper.JsonMapper;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Set;


@ComponentScan
public class Application {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Application.class);
        JsonMapper jsonMapper = applicationContext.getBean(JsonMapper.class);

        String firstLocation = "{\"id\":\"1\",\"title\":\"1\",\"description\":\"1\",\"ageLimit\":\"1\",\"occupiedPlace\":\"1\",\"date\":\"1\"}";

        EventDto event = jsonMapper.toEntity(firstLocation, EventDto.class);

        TicketController ticketController = applicationContext.getBean(TicketController.class);

        Set<TicketDto> tickets = ticketController.getEventTickets(event);

        System.out.println("1");


        String secondEntity = "{\"id\":\"1\",\"login\":\"1\",\"password\":\"1\",\"email\":\"1\",\"phoneNumber\":\"1\",\"firstName\":\"1\",\"surname\":\"1\"}";

        UserDto userDto = jsonMapper.toEntity(secondEntity, UserDto.class);

        Set<TicketDto> tkts = ticketController.getUserTickets(userDto);

        System.out.println("1234");

    }

}
