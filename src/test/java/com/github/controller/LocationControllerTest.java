package com.github.controller;

import com.github.WebAppInitializer;
import com.github.configs.root.ApplicationConfig;
import com.github.configs.root.DatabaseConfig;
import com.github.dto.LocationDto;
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

@Transactional
@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfig.class, WebAppInitializer.class, DatabaseConfig.class })
public class LocationControllerTest {

    @Autowired
    private JsonMapper jsonMapper;

    @Autowired
    private LocationController locationController;

    private LocationDto locationDto;

    private String jsonBody;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(locationController).build();

        locationDto = new LocationDto();
        locationDto.setAddress("Test");
        locationDto.setTitle("NN");
        locationDto.setCapacity(12);
    }

    @Test
    @Transactional(readOnly = true)
    public void createLocationSuccess() throws Exception {

        this.jsonBody = jsonMapper.toJson(locationDto);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/location-management")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.title",
                                CoreMatchers.is(locationDto.getTitle())))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.address",
                                CoreMatchers.is(locationDto.getAddress())))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.capacity",
                                CoreMatchers.is(locationDto.getCapacity())));

    }

    @Test
    @Transactional(readOnly = true)
    public void readLocationSuccess() throws Exception {

        LocationDto locationDtoMock = new LocationDto();
        locationDtoMock.setId(2);
        locationDtoMock.setAddress("Test");
        locationDtoMock.setTitle("NN");
        locationDtoMock.setCapacity(12);

        LocationDto location = locationController.createLocation(locationDtoMock);

        this.jsonBody = jsonMapper.toJson(location);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/location-management/{locationId}", location.getId()))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.title",
                                CoreMatchers.is(locationDtoMock.getTitle())))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.address",
                                CoreMatchers.is(locationDtoMock.getAddress())))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.capacity",
                                CoreMatchers.is(locationDtoMock.getCapacity())));

    }

    @Test
    public void updateLocationSuccess() throws Exception {

        locationDto.setId(3);
        locationDto.setTitle("test123");

        LocationDto location = locationController.createLocation(locationDto);

        this.jsonBody = jsonMapper.toJson(location);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .put("/location-management/{locationId}", location.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.title",
                                CoreMatchers.is(locationDto.getTitle())));
    }

    @Test
    public void deleteLocationSuccess() throws Exception {

        LocationDto locationDto = new LocationDto();
        locationDto.setId(5);
        locationDto.setAddress("123");
        locationDto.setTitle("1234");
        locationDto.setCapacity(22);

        locationController.createLocation(locationDto);

        this.jsonBody = jsonMapper.toJson(locationDto);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/location-management/{locationId}", locationDto.getId()))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

    }

}
