package com.github.controller;

import com.github.WebAppInitializer;
import com.github.configs.root.ApplicationConfig;
import com.github.configs.root.DatabaseConfig;
import com.github.dto.LocationDto;
import com.github.mapper.impl.JsonMapper;
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

    private String jsonBody;

    private MockMvc mockMvc;

    private LocationDto expectedLocationDto;

    @Autowired
    private JsonMapper jsonMapper;

    @Autowired
    private LocationController locationController;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(locationController).build();

        expectedLocationDto = new LocationDto();
        expectedLocationDto.setAddress("Misery avenue");
        expectedLocationDto.setTitle("Armed & Dangerous");
        expectedLocationDto.setCapacity(99);
    }

    @Test
    @Transactional(readOnly = true)
    public void createLocationSuccess() throws Exception {

        this.jsonBody = jsonMapper.toJson(expectedLocationDto);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/location-management")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.title",
                                CoreMatchers.is(expectedLocationDto.getTitle())))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.address",
                                CoreMatchers.is(expectedLocationDto.getAddress())))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.capacity",
                                CoreMatchers.is(expectedLocationDto.getCapacity())));

    }

    @Test
    @Transactional(readOnly = true)
    public void readLocationSuccess() throws Exception {

        expectedLocationDto = locationController.createLocation(expectedLocationDto);

        this.jsonBody = jsonMapper.toJson(expectedLocationDto);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/location-management/{locationId}", expectedLocationDto.getId()))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.title",
                                CoreMatchers.is(expectedLocationDto.getTitle())))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.address",
                                CoreMatchers.is(expectedLocationDto.getAddress())))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.capacity",
                                CoreMatchers.is(expectedLocationDto.getCapacity())));

    }

    @Test
    public void updateLocationSuccess() throws Exception {

        expectedLocationDto = locationController.createLocation(expectedLocationDto);
        expectedLocationDto.setTitle("I'm Still");

        this.jsonBody = jsonMapper.toJson(expectedLocationDto);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .put("/location-management/{locationId}", expectedLocationDto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.title",
                                CoreMatchers.is(expectedLocationDto.getTitle())));
    }

    @Test
    public void deleteLocationSuccess() throws Exception {

        expectedLocationDto = locationController.createLocation(expectedLocationDto);

        this.jsonBody = jsonMapper.toJson(expectedLocationDto);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/location-management/{locationId}", expectedLocationDto.getId()))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

    }

}
