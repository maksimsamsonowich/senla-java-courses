package com.github.controller;

import com.github.WebAppInitializer;
import com.github.configs.root.ApplicationConfig;
import com.github.configs.root.DatabaseConfig;
import com.github.dto.CredentialDto;
import com.github.dto.UserDto;
import com.github.mapper.impl.JsonMapper;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

@Transactional
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ApplicationConfig.class, WebAppInitializer.class, DatabaseConfig.class })
public class UserControllerTest {


    private String jsonBody;

    private UserDto userDto;

    private MockMvc mockMvc;

    @Autowired
    private JsonMapper jsonMapper;

    @Autowired
    private UserController userController;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        userDto = new UserDto()
                .setFirstName("max")
                .setSurname("max")
                .setPhoneNumber("+375999999999")
                .setCredentials(new CredentialDto()
                        .setId(1L));
    }

    @Test
    @WithMockUser(username = "fightingdemons@gmail.com", roles = "ADMIN")
    public void createUserSuccess() throws Exception {

        this.jsonBody = jsonMapper.toJson(userDto);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/user-management")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName",
                        CoreMatchers.is(userDto.getFirstName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname",
                        CoreMatchers.is(userDto.getSurname())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber",
                        CoreMatchers.is(userDto.getPhoneNumber())));

    }

    @Test
    @Transactional(readOnly = true)
    @WithMockUser(username = "fightingdemons@gmail.com", roles = "ADMIN")
    public void readUserSuccess() throws Exception {

        userDto = userController.createUser(userDto).getBody();

        this.jsonBody = jsonMapper.toJson(userDto);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/user-management/{id}", userDto.getId()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName",
                        CoreMatchers.is(userDto.getFirstName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname",
                        CoreMatchers.is(userDto.getSurname())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber",
                        CoreMatchers.is(userDto.getPhoneNumber())));

    }

    @Test
    @WithMockUser(username = "fightingdemons@gmail.com", roles = "ADMIN")
    public void updateUserSuccess() throws Exception {

        userDto = userController.createUser(userDto).getBody();
        userDto.setFirstName("maxmax");

        this.jsonBody = jsonMapper.toJson(userDto);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .put("/user-management/{id}", userDto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName",
                        CoreMatchers.is(userDto.getFirstName())));

    }

    @Test
    @WithMockUser(username = "fightingdemons@gmail.com", roles = "ADMIN")
    public void deleteUserSuccess() throws Exception {

        userDto = userController.createUser(userDto).getBody();

        this.jsonBody = jsonMapper.toJson(userDto);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/user-management/{id}", userDto.getId()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        Assert.assertNull(userController.readUser(userDto.getId()).getBody());

    }

}
