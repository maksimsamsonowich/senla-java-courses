package com.github.controller;

import com.github.WebAppInitializer;
import com.github.configs.root.ApplicationConfig;
import com.github.configs.root.DatabaseConfig;
import com.github.dao.LocationDao;
import com.github.dto.LocationDto;
import com.github.entity.Location;
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

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ApplicationConfig.class, WebAppInitializer.class, DatabaseConfig.class })
public class LocationControllerTest {

    @Autowired
    private JsonMapper jsonMapper;

    @Autowired
    private LocationController locationController;

    @Autowired
    private LocationDao locationDao;

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
    public void givenLocationDto_whenSave_thenOk() throws Exception {

        this.jsonBody = jsonMapper.toJson(locationDto);

        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/location-management")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                        .andDo(print())
                        .andExpect(status().is2xxSuccessful())
                        .andReturn();

        int jsonIdResult = JsonPath.read(result.getResponse().getContentAsString(), "$.id");
        String jsonAddressResult = JsonPath.read(result.getResponse().getContentAsString(), "$.address");
        String jsonTitleResult = JsonPath.read(result.getResponse().getContentAsString(), "$.title");
        int jsonCapacityResult = JsonPath.read(result.getResponse().getContentAsString(), "$.capacity");

        Location event = locationDao.read(jsonIdResult);

        Assert.assertEquals(event.getId(), jsonIdResult);
        Assert.assertEquals(event.getTitle(), jsonTitleResult);
        Assert.assertEquals(event.getAddress(), jsonAddressResult);
        Assert.assertEquals(event.getCapacity(), jsonCapacityResult);

    }

    @Test
    public void givenLocationId_whenRead_thenOk() throws Exception {

        LocationDto locationDtoMock = new LocationDto();
        locationDtoMock.setId(2);
        locationDtoMock.setAddress("Test");
        locationDtoMock.setTitle("NN");
        locationDtoMock.setCapacity(12);

        LocationDto location = locationController.createLocation(locationDtoMock);

        this.jsonBody = jsonMapper.toJson(location);

        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/location-management/{locationId}", location.getId()))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn();

        int jsonIdResult = JsonPath.read(result.getResponse().getContentAsString(), "$.id");
        String jsonAddressResult = JsonPath.read(result.getResponse().getContentAsString(), "$.address");
        String jsonTitleResult = JsonPath.read(result.getResponse().getContentAsString(), "$.title");
        int jsonCapacityResult = JsonPath.read(result.getResponse().getContentAsString(), "$.capacity");

        Location event = locationDao.read(jsonIdResult);

        Assert.assertEquals(event.getId(), jsonIdResult);
        Assert.assertEquals(event.getTitle(), jsonTitleResult);
        Assert.assertEquals(event.getAddress(), jsonAddressResult);
        Assert.assertEquals(event.getCapacity(), jsonCapacityResult);

    }

    @Test
    public void givenLocation_whenUpdate_thenOk() throws Exception {

        locationDto.setId(3);
        locationDto.setTitle("test123");

        LocationDto location = locationController.createLocation(locationDto);

        this.jsonBody = jsonMapper.toJson(location);

        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders
                        .put("/location-management/{locationId}", location.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn();

        String jsonTitleResult = JsonPath.read(result.getResponse().getContentAsString(), "$.title");

        Assert.assertEquals(location.getTitle(), jsonTitleResult);

    }

    @Test
    public void givenLocation_whenDelete_thenOk() throws Exception {

        LocationDto locationDto = new LocationDto();
        locationDto.setId(5);
        locationDto.setAddress("123");
        locationDto.setTitle("1234");
        locationDto.setCapacity(22);

        locationController.createLocation(locationDto);

        this.jsonBody = jsonMapper.toJson(locationDto);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/location-management/{locationId}", locationDto.getId()))
                .andDo(print())
                .andExpect(status().isOk());

    }

}
