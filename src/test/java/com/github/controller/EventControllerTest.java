package com.github.controller;

import com.github.WebAppInitializer;
import com.github.configs.root.ApplicationConfig;
import com.github.configs.root.DatabaseConfig;
import com.github.dao.EventDao;
import com.github.dto.EventDto;
import com.github.dto.LocationDto;
import com.github.entity.Event;
import com.github.mapper.JsonMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.Assert;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Date;
import java.util.Set;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(SpringExtension.class)
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
    public void givenEventDto_whenSave_thenOk() throws Exception {

        this.jsonBody = jsonMapper.toJson(eventDto);

        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/event-management")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                        .andDo(print())
                        .andExpect(status().is2xxSuccessful())
                        .andReturn();

        int jsonIdResult = JsonPath.read(result.getResponse().getContentAsString(), "$.id");
        String jsonTitleResult = JsonPath.read(result.getResponse().getContentAsString(), "$.title").toString();
        String jsonDescriptionResult = JsonPath.read(result.getResponse().getContentAsString(), "$.description");
        int jsonAgeLimitResult = JsonPath.read(result.getResponse().getContentAsString(), "$.ageLimit");
        int jsonOccupiedPlaceResult = JsonPath.read(result.getResponse().getContentAsString(), "$.occupiedPlace");

        Event event = eventDao.read(jsonIdResult);

        Assert.assertEquals(event.getId(), jsonIdResult);
        Assert.assertEquals(event.getTitle(), jsonTitleResult);
        Assert.assertEquals(event.getDescription(), jsonDescriptionResult);
        Assert.assertEquals(event.getAgeLimit(), jsonAgeLimitResult);
        Assert.assertEquals(event.getOccupiedPlace(), jsonOccupiedPlaceResult);
    }

    @Test
    public void givenEventId_whenRead_thenOk() throws Exception {

        this.jsonBody = jsonMapper.toJson(eventDto);

        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/event-management/{eventId}", 1))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn();

        int jsonIdResult = JsonPath.read(result.getResponse().getContentAsString(), "$.id");
        String jsonTitleResult = JsonPath.read(result.getResponse().getContentAsString(), "$.title");
        String jsonDescriptionResult = JsonPath.read(result.getResponse().getContentAsString(), "$.description");
        int jsonAgeLimitResult = JsonPath.read(result.getResponse().getContentAsString(), "$.ageLimit");
        int jsonOccupiedPlaceResult = JsonPath.read(result.getResponse().getContentAsString(), "$.occupiedPlace");

        Event event = eventDao.read(jsonIdResult);

        Assert.assertEquals(event.getId(), jsonIdResult);
        Assert.assertEquals(event.getTitle(), jsonTitleResult);
        Assert.assertEquals(event.getDescription(), jsonDescriptionResult);
        Assert.assertEquals(event.getAgeLimit(), jsonAgeLimitResult);
        Assert.assertEquals(event.getOccupiedPlace(), jsonOccupiedPlaceResult);

    }

    @Test
    public void givenEvent_whenUpdate_thenOk() throws Exception {

        eventDto.setId(1);
        eventDto.setTitle("Edited title");

        this.jsonBody = jsonMapper.toJson(eventDto);

        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders
                .put("/event-management/{eventId}", eventDto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Event event = eventDao.read(eventDto.getId());

        String jsonTitleResult = JsonPath.read(result.getResponse().getContentAsString(), "$.title");

        Assert.assertEquals(event.getTitle(), jsonTitleResult);
    }

    @Test
    public void givenEvent_whenDelete_thenOk() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .delete("/event-management/{eventId}", eventDto.getId()))
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

        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders
                .get("/event-management/by-location/{locationId}", locationDto.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Set<Event> events = eventDao.getEventsByLocation(locationDto.getId());

        int iterator = 0;

        for(Event event : events) {
            int jsonEventIdActual = JsonPath.read(result.getResponse().getContentAsString(), "$.[" + iterator + "].id");

            Assert.assertEquals(event.getId(), jsonEventIdActual);

            String jsonTitleResult = JsonPath.read(result.getResponse().getContentAsString(), "$.[" + iterator + "].title");

            Assert.assertEquals(event.getTitle(), jsonTitleResult);

            String jsonDescriptionResult = JsonPath.read(result.getResponse().getContentAsString(), "$.[" + iterator + "].description");

            Assert.assertEquals(event.getDescription(), jsonDescriptionResult);

            int jsonAgeLimitResult = JsonPath.read(result.getResponse().getContentAsString(), "$.[" + iterator + "].ageLimit");

            Assert.assertEquals(event.getAgeLimit(), jsonAgeLimitResult);

            int jsonOccupiedPlaceResult = JsonPath.read(result.getResponse().getContentAsString(), "$.[" + iterator + "].occupiedPlace");

            Assert.assertEquals(event.getOccupiedPlace(), jsonOccupiedPlaceResult);

            iterator++;
        }
    }

}
