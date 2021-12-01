package com.github.controller;

import com.github.WebAppInitializer;
import com.github.configs.root.ApplicationConfig;
import com.github.configs.root.DatabaseConfig;
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

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ApplicationConfig.class, WebAppInitializer.class, DatabaseConfig.class })
public class LocationControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private JsonMapper jsonMapper;

    private LocationController locationController;

    private LocationDto locationDto;

    private String jsonBody;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        this.locationController = (LocationController) webApplicationContext.getBean("locationController");

        locationDto = new LocationDto();
        locationDto.setId(1);
        locationDto.setAddress("Кабяка 11");
        locationDto.setTitle("NN");
        locationDto.setCapacity(12);
    }

    @Test
    public void givenLocationDto_whenSave_thenOk() throws Exception {

        locationDto.setId(2);

        this.jsonBody = jsonMapper.toJson(locationDto);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/location-management/{locationId}", locationDto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andDo(print()).andExpect(status().isOk());

    }

    @Test
    public void givenLocationId_whenRead_thenOk() throws Exception {

        this.jsonBody = jsonMapper.toJson(locationDto);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/location-management/{locationId}", 1))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void givenLocation_whenUpdate_thenOk() throws Exception {

        this.jsonBody = jsonMapper.toJson(locationDto);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .put("/location-management/{locationId}", locationDto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void givenLocation_whenDelete_thenOk() throws Exception {

        LocationDto locationDto = new LocationDto();
        locationDto.setId(5);
        locationDto.setAddress("123");
        locationDto.setTitle("1234");
        locationDto.setCapacity(22);

        locationController.createLocation(locationDto.getId(), locationDto);

        this.jsonBody = jsonMapper.toJson(locationDto);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/location-management/{locationId}", locationDto.getId()))
                .andDo(print())
                .andExpect(status().isOk());

    }

}
