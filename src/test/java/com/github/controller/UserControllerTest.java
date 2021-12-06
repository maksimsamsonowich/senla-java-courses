package com.github.controller;

import com.github.WebAppInitializer;
import com.github.configs.root.ApplicationConfig;
import com.github.configs.root.DatabaseConfig;
import com.github.dto.UserDto;
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
    public void createUserSuccess() throws Exception {

        this.jsonBody = jsonMapper.toJson(userDto);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/user-management")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.login",
                                CoreMatchers.is(userDto.getLogin())))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.password",
                                CoreMatchers.is(userDto.getPassword())))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.email",
                                CoreMatchers.is(userDto.getEmail())))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.firstName",
                                CoreMatchers.is(userDto.getFirstName())))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.surname",
                                CoreMatchers.is(userDto.getSurname())))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber",
                                CoreMatchers.is(userDto.getPhoneNumber())));

    }

    @Test
    @Transactional(readOnly = true)
    public void readUserSuccess() throws Exception {

        userDto = userController.createUser(userDto);

        this.jsonBody = jsonMapper.toJson(userDto);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/user-management/{id}", userDto.getId()))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.login",
                                CoreMatchers.is(userDto.getLogin())))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.password",
                                CoreMatchers.is(userDto.getPassword())))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.email",
                                CoreMatchers.is(userDto.getEmail())))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.firstName",
                                CoreMatchers.is(userDto.getFirstName())))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.surname",
                                CoreMatchers.is(userDto.getSurname())))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber",
                                CoreMatchers.is(userDto.getPhoneNumber())));

    }

    @Test
    public void updateUserSuccess() throws Exception {

        userDto.setLogin("maxmax");

        this.jsonBody = jsonMapper.toJson(userDto);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .put("/user-management/{id}", userDto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.login",
                        CoreMatchers.is(userDto.getLogin())));

    }

    @Test
    public void deleteUserSuccess() throws Exception {

        userDto = userController.createUser(userDto);

        this.jsonBody = jsonMapper.toJson(userDto);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/user-management/{id}", userDto.getId()))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk());

    }

}
