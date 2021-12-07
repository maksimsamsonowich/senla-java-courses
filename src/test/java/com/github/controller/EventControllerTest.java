package com.github.controller;

import com.github.WebAppInitializer;
import com.github.configs.root.ApplicationConfig;
import com.github.configs.root.DatabaseConfig;
import com.github.dto.EventDto;
import com.github.entity.Event;
import com.github.mapper.impl.JsonMapper;
import com.github.repository.impl.EventRepository;
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

    private String jsonBody;

    private MockMvc mockMvc;

    private EventDto expectedEventDto;

    @Autowired
    private EventRepository eventDao;

    @Autowired
    private JsonMapper jsonMapper;

    @Autowired
    private EventController eventController;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(eventController).build();

        expectedEventDto = new EventDto();
        expectedEventDto.setTitle("Candles");
        expectedEventDto.setDescription("Tryna put a diamond ring on her finger");
        expectedEventDto.setAgeLimit((short) 21);
        expectedEventDto.setOccupiedPlace((short) 99);
        expectedEventDto.setDate(Date.valueOf("2021-11-29"));
    }

    @Test
    @Transactional(readOnly = true)
    public void createEventSuccess() throws Exception {

        this.jsonBody = jsonMapper.toJson(expectedEventDto);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/event-management")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.title",
                                CoreMatchers.is(expectedEventDto.getTitle())))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.description",
                                CoreMatchers.is(expectedEventDto.getDescription())))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.ageLimit",
                                CoreMatchers.is(expectedEventDto.getAgeLimit())))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.occupiedPlace",
                                CoreMatchers.is(expectedEventDto.getOccupiedPlace())));
    }

    @Test
    @Transactional(readOnly = true)
    public void readEventSuccess() throws Exception {

        expectedEventDto = eventController.createEvent(expectedEventDto).getBody();

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/event-management/{eventId}", expectedEventDto.getId()))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.title",
                                CoreMatchers.is(expectedEventDto.getTitle())))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.description",
                                CoreMatchers.is(expectedEventDto.getDescription())))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.ageLimit",
                                CoreMatchers.is(expectedEventDto.getAgeLimit())))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.occupiedPlace",
                                CoreMatchers.is(expectedEventDto.getOccupiedPlace())));

    }

    @Test
    public void updateEventSuccess() throws Exception {

        expectedEventDto = eventController.createEvent(expectedEventDto).getBody();
        expectedEventDto.setTitle("Doom");

        this.jsonBody = jsonMapper.toJson(expectedEventDto);

        this.mockMvc.perform(MockMvcRequestBuilders
                .put("/event-management/{eventId}", expectedEventDto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title",
                        CoreMatchers.is(expectedEventDto.getTitle())));
    }

    @Test
    public void deleteEventSuccess() throws Exception {

        expectedEventDto = eventController.createEvent(expectedEventDto).getBody();

        this.mockMvc.perform(MockMvcRequestBuilders
                .delete("/event-management/{eventId}", expectedEventDto.getId()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @Transactional(readOnly = true)
    public void getEventsByLocationSuccess() throws Exception{

        expectedEventDto.setId(1);
        Event expectedEventDto = eventDao.getEventsByLocation(this.expectedEventDto.getId()).iterator().next();

        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/event-management/by-location/{locationId}", expectedEventDto.getId()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].title",
                        CoreMatchers.is(expectedEventDto.getTitle())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].description",
                        CoreMatchers.is(expectedEventDto.getDescription())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].ageLimit",
                        CoreMatchers.is((int) expectedEventDto.getAgeLimit())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].occupiedPlace",
                        CoreMatchers.is((int) expectedEventDto.getOccupiedPlace())));
    }

}
