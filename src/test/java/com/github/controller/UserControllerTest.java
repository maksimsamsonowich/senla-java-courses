package com.github.controller;

import com.github.WebAppInitializer;
import com.github.configs.root.ApplicationConfig;
import com.github.configs.root.DatabaseConfig;
import com.github.dto.UserDto;
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
@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ApplicationConfig.class, WebAppInitializer.class, DatabaseConfig.class })
public class UserControllerTest {

    private String jsonBody;

    private UserDto expectedUserDto;

    private MockMvc mockMvc;

    @Autowired
    private JsonMapper jsonMapper;

    @Autowired
    private UserController userController;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        expectedUserDto = new UserDto();
        expectedUserDto.setFirstName("motzisudo");
        expectedUserDto.setSurname("motzisudo");
        expectedUserDto.setPhoneNumber("+375999999999");
    }

    @Test
    @Transactional(readOnly = true)
    public void createUserSuccess() throws Exception {

        this.jsonBody = jsonMapper.toJson(expectedUserDto);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/user-management")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.firstName",
                                CoreMatchers.is(expectedUserDto.getFirstName())))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.surname",
                                CoreMatchers.is(expectedUserDto.getSurname())))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber",
                                CoreMatchers.is(expectedUserDto.getPhoneNumber())));

    }

    @Test
    @Transactional(readOnly = true)
    public void readUserSuccess() throws Exception {

        expectedUserDto = userController.createUser(expectedUserDto);

        this.jsonBody = jsonMapper.toJson(expectedUserDto);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/user-management/{id}", expectedUserDto.getId()))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.firstName",
                                CoreMatchers.is(expectedUserDto.getFirstName())))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.surname",
                                CoreMatchers.is(expectedUserDto.getSurname())))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber",
                                CoreMatchers.is(expectedUserDto.getPhoneNumber())));

    }

    @Test
    public void updateUserSuccess() throws Exception {

        expectedUserDto = userController.createUser(expectedUserDto);
        expectedUserDto.setSurname("maxmax");

        this.jsonBody = jsonMapper.toJson(expectedUserDto);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .put("/user-management/{id}", expectedUserDto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.surname",
                        CoreMatchers.is(expectedUserDto.getSurname())));

    }

    @Test
    public void deleteUserSuccess() throws Exception {

        expectedUserDto = userController.createUser(expectedUserDto);

        this.jsonBody = jsonMapper.toJson(expectedUserDto);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/user-management/{id}", expectedUserDto.getId()))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk());

    }

}
