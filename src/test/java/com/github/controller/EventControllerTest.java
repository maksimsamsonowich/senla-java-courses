package com.github.controller;

import com.github.WebAppInitializer;
import com.github.configs.root.ApplicationConfig;
import com.github.configs.root.DatabaseConfig;
import com.github.dao.EventDao;
import com.github.dto.EventDto;
import com.github.entity.Event;
import com.github.mapper.JsonMapper;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

@Transactional
@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfig.class, WebAppInitializer.class, DatabaseConfig.class })
public class EventControllerTest {

    @Autowired
    private JsonMapper jsonMapper;

    @Autowired
    private EventController eventController;

    @Autowired
    private EventDao eventDao;

    private EventDto eventDto;

    private String jsonBody;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(eventController).build();

        eventDto = new EventDto();
        eventDto.setTitle("Title");
        eventDto.setDescription("Desc");
        eventDto.setAgeLimit((short) 18);
        eventDto.setOccupiedPlace((short) 11);
        eventDto.setDate(Date.valueOf("2021-11-29"));
    }

    @Test
    public void createEventSuccess() throws Exception {

        this.jsonBody = jsonMapper.toJson(eventDto);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/event-management")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.title",
                                CoreMatchers.is(eventDto.getTitle())))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.description",
                                CoreMatchers.is(eventDto.getDescription())))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.ageLimit",
                                CoreMatchers.is(eventDto.getAgeLimit())))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.occupiedPlace",
                                CoreMatchers.is(eventDto.getOccupiedPlace())));
    }

    @Test
    @Transactional(readOnly = true)
    public void readEventSuccess() throws Exception {

        eventDto = eventController.createEvent(eventDto);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/event-management/{eventId}", eventDto.getId()))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.title",
                                CoreMatchers.is(eventDto.getTitle())))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.description",
                                CoreMatchers.is(eventDto.getDescription())))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.ageLimit",
                                CoreMatchers.is(eventDto.getAgeLimit())))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.occupiedPlace",
                                CoreMatchers.is(eventDto.getOccupiedPlace())));

    }

    @Test
    public void updateEventSuccess() throws Exception {

        eventDto.setId(2);
        eventDto.setTitle("Edited title");

        this.jsonBody = jsonMapper.toJson(eventDto);

        this.mockMvc.perform(MockMvcRequestBuilders
                .put("/event-management/{eventId}", eventDto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title",
                        CoreMatchers.is(eventDto.getTitle())));
    }

    @Test
    public void deleteEventSuccess() throws Exception {

        eventDto = eventController.createEvent(eventDto);

        this.mockMvc.perform(MockMvcRequestBuilders
                .delete("/event-management/{eventId}", eventDto.getId()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @Transactional(readOnly = true)
    public void getEventsByLocationSuccess() throws Exception{

        eventDto.setId(1);

        Event event = eventDao.getEventsByLocation(eventDto.getId()).iterator().next();

        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/event-management/by-location/{locationId}", eventDto.getId()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].title",
                        CoreMatchers.is(event.getTitle())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].description",
                        CoreMatchers.is(event.getDescription())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].ageLimit",
                        CoreMatchers.is((int) event.getAgeLimit())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].occupiedPlace",
                        CoreMatchers.is((int) event.getOccupiedPlace())));
    }

}
