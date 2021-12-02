package com.github.controller;

import com.github.WebAppInitializer;
import com.github.configs.root.ApplicationConfig;
import com.github.configs.root.DatabaseConfig;
import com.github.dto.UserDto;
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
public class UserControllerTest {

    @Autowired
    private JsonMapper jsonMapper;

    @Autowired
    private UserController userController;

    private UserDto userDto;

    private String jsonBody;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        userDto = new UserDto();
        userDto.setLogin("max");
        userDto.setPassword("max");
        userDto.setEmail("max");
        userDto.setFirstName("max");
        userDto.setSurname("max");
        userDto.setPhoneNumber("+375999999999");
    }

    @Test
    public void givenUserDto_whenSave_thenOk() throws Exception {

        this.jsonBody = jsonMapper.toJson(userDto);

        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post("/user-management")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                        .andDo(print())
                        .andExpect(status().is2xxSuccessful())
                        .andReturn();

        int jsonIdResult = JsonPath.read(result.getResponse().getContentAsString(), "$.id");
        String jsonLoginResult = JsonPath.read(result.getResponse().getContentAsString(), "$.login");
        String jsonPasswordResult = JsonPath.read(result.getResponse().getContentAsString(), "$.password");
        String jsonEmailResult = JsonPath.read(result.getResponse().getContentAsString(), "$.email");
        String jsonFirstNameResult = JsonPath.read(result.getResponse().getContentAsString(), "$.firstName");
        String jsonSurnameResult = JsonPath.read(result.getResponse().getContentAsString(), "$.surname");
        String jsonPhoneNumberResult = JsonPath.read(result.getResponse().getContentAsString(), "$.phoneNumber");
        UserDto userDtoMock = userController.readUser(userDto.getId());

        Assert.assertEquals(userDtoMock.getId(), jsonIdResult);
        Assert.assertEquals(userDtoMock.getLogin(), jsonLoginResult);
        Assert.assertEquals(userDtoMock.getPassword(), jsonPasswordResult);
        Assert.assertEquals(userDtoMock.getEmail(), jsonEmailResult);
        Assert.assertEquals(userDtoMock.getFirstName(), jsonFirstNameResult);
        Assert.assertEquals(userDtoMock.getSurname(), jsonSurnameResult);
        Assert.assertEquals(userDtoMock.getPhoneNumber(), jsonPhoneNumberResult);


    }

    @Test
    public void givenUserId_whenRead_thenOk() throws Exception {

        this.jsonBody = jsonMapper.toJson(userDto);

        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/user-management/{id}", userDto.getId()))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        int jsonIdResult = JsonPath.read(result.getResponse().getContentAsString(), "$.id");
        String jsonLoginResult = JsonPath.read(result.getResponse().getContentAsString(), "$.login");
        String jsonPasswordResult = JsonPath.read(result.getResponse().getContentAsString(), "$.password");
        String jsonEmailResult = JsonPath.read(result.getResponse().getContentAsString(), "$.email");
        String jsonFirstNameResult = JsonPath.read(result.getResponse().getContentAsString(), "$.firstName");
        String jsonSurnameResult = JsonPath.read(result.getResponse().getContentAsString(), "$.surname");
        String jsonPhoneNumberResult = JsonPath.read(result.getResponse().getContentAsString(), "$.phoneNumber");
        UserDto userDtoMock = userController.readUser(userDto.getId());

        Assert.assertEquals(userDtoMock.getId(), jsonIdResult);
        Assert.assertEquals(userDtoMock.getLogin(), jsonLoginResult);
        Assert.assertEquals(userDtoMock.getPassword(), jsonPasswordResult);
        Assert.assertEquals(userDtoMock.getEmail(), jsonEmailResult);
        Assert.assertEquals(userDtoMock.getFirstName(), jsonFirstNameResult);
        Assert.assertEquals(userDtoMock.getSurname(), jsonSurnameResult);
        Assert.assertEquals(userDtoMock.getPhoneNumber(), jsonPhoneNumberResult);

    }

    @Test
    public void givenUser_whenUpdate_thenOk() throws Exception {

        userDto.setLogin("maxmax");

        this.jsonBody = jsonMapper.toJson(userDto);

        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders
                        .put("/user-management/{id}", userDto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String jsonLoginResult = JsonPath.read(result.getResponse().getContentAsString(), "$.login");

        Assert.assertEquals(userDto.getLogin(), jsonLoginResult);

    }

    @Test
    public void givenTicket_whenDelete_thenOk() throws Exception {

        UserDto userDto = new UserDto();
        userDto.setId(5);
        userDto.setEmail("jksaf");
        userDto.setLogin("2344213");
        userDto.setPassword("423sgdk");
        userDto.setFirstName("123");
        userDto.setSurname("4123");
        userDto.setPhoneNumber("349219423");

        userController.createUser(userDto);

        this.jsonBody = jsonMapper.toJson(userDto);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/user-management/{id}", userDto.getId()))
                .andDo(print())
                .andExpect(status().isOk());

    }

}
