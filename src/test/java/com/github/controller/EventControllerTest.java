package com.github.controller;

import com.github.WebAppInitializer;
import com.github.configs.root.ApplicationConfig;
import com.github.configs.root.DatabaseConfig;
import com.github.dto.EventArtistDto;
import com.github.dto.EventDto;
import com.github.dto.EventProgramDto;
import com.github.dto.LocationDto;
import com.github.entity.Event;
import com.github.mapper.impl.JsonMapper;
import com.github.repository.impl.EventRepository;
import io.jsonwebtoken.lang.Assert;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
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
import java.util.Objects;

@Transactional
@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfig.class, WebAppInitializer.class, DatabaseConfig.class })
public class EventControllerTest {

    private String jsonBody;

    private MockMvc mockMvc;

    private EventDto eventDto;

    private LocationDto locationDto;

    @Autowired
    private JsonMapper jsonMapper;

    @Autowired
    private EventController eventController;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(eventController).build();

        eventDto = new EventDto()
                .setTitle("Title")
                .setDescription("Desc")
                .setAgeLimit((short) 18)
                .setOccupiedPlace((short) 11)
                .setDate(Date.valueOf("2021-11-29"))
                .setEventOrganizer(new EventArtistDto()
                        .setId(1L));

        locationDto = new LocationDto().setId(1L);
    }

    @Test
    @WithMockUser(username = "fightingdemons@gmail.com", roles = "ADMIN")
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
    @WithMockUser(username = "fightingdemons@gmail.com", roles = "ADMIN")
    public void readEventSuccess() throws Exception {

        eventDto = eventController.createEvent(eventDto).getBody();

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
    @WithMockUser(username = "fightingdemons@gmail.com", roles = "ADMIN")
    public void updateEventSuccess() throws Exception {

        eventDto = eventController.createEvent(eventDto).getBody();
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
    @WithMockUser(username = "fightingdemons@gmail.com", roles = "ADMIN")
    public void deleteEventSuccess() throws Exception {

        eventDto = eventController.createEvent(eventDto).getBody();

        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/event-management/{eventId}", eventDto.getId()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        Assert.isNull(eventController.readEvent(eventDto.getId()).getBody());

    }

    @Test
    @Transactional(readOnly = true)
    @WithMockUser(username = "fightingdemons@gmail.com", roles = "ADMIN")
    public void getEventsByLocationSuccess() throws Exception{

        eventDto = Objects.requireNonNull(eventController.getEventsByLocation(locationDto.getId())
                .getBody()).iterator().next();

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/event-management/by-location/{locationId}", locationDto.getId()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].title",
                        CoreMatchers.is(eventDto.getTitle())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].description",
                        CoreMatchers.is(eventDto.getDescription())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].ageLimit",
                        CoreMatchers.is((int) eventDto.getAgeLimit())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].occupiedPlace",
                        CoreMatchers.is((int) eventDto.getOccupiedPlace())));
    }
}
