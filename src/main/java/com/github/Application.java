package com.github;

import com.github.configs.ApplicationConfig;
import com.github.controller.EventController;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class Application {

    
    /*public static void main(String[] args) {

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

    }*/

}
