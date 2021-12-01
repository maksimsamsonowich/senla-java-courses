package com.github.controller;

import com.github.WebAppInitializer;
import com.github.configs.root.ApplicationConfig;
import com.github.configs.root.DatabaseConfig;
import com.github.dto.EventDto;
import com.github.dto.LocationDto;
import com.github.mapper.JsonMapper;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ApplicationConfig.class, WebAppInitializer.class, DatabaseConfig.class })
public class EventControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private JsonMapper jsonMapper;

    private EventDto eventDto;

    private String jsonBody;

    private MockMvc mockMvc;

    private EventController eventController;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        this.eventController = (EventController) webApplicationContext.getBean("eventController");

        eventDto = new EventDto();
        eventDto.setId(2);
        eventDto.setTitle("Title");
        eventDto.setDescription("Desc");
        eventDto.setAgeLimit((short) 18);
        eventDto.setOccupiedPlace((short) 11);
        eventDto.setDate(Date.valueOf("2021-11-29"));
    }

    @Test
    public void givenEventDto_whenSave_thenOk() throws Exception {

        eventDto.setId(2);

        this.jsonBody = jsonMapper.toJson(eventDto);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/event-management/{id}}", eventDto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                        .andDo(print()).andExpect(status().isOk());

    }

    @Test
    public void givenEventId_whenRead_thenOk() throws Exception {

        this.jsonBody = jsonMapper.toJson(eventDto);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/event-management/{id}", 1))
                        .andDo(print())
                        .andExpect(status().isOk());

    }

    @Test
    public void givenEvent_whenUpdate_thenOk() throws Exception {

        this.jsonBody = jsonMapper.toJson(eventDto);

        this.mockMvc.perform(MockMvcRequestBuilders
                .put("/event-management/{id}")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void givenEvent_whenDelete_thenOk() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .delete("/event-management/{id}", eventDto.getId()))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void givenLocation_whenGetEventByLocation_thenOk() throws Exception{

        LocationDto locationDto
                = new LocationDto();
        locationDto.setId(1);
        locationDto.setAddress("Кабяка 11");
        locationDto.setTitle("NN");
        locationDto.setCapacity(12);

        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/event-management/by-location")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.toJson(locationDto)))
                .andDo(print())
                .andExpect(status().isOk());

    }

}
