package com.github.controller;

import com.github.WebAppInitializer;
import com.github.configs.root.ApplicationConfig;
import com.github.configs.root.DatabaseConfig;
import com.github.dto.UserDto;
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
public class UserControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private JsonMapper jsonMapper;

    private UserDto userDto;

    private String jsonBody;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();

        userDto = new UserDto();
        userDto.setId(1);
        userDto.setLogin("max");
        userDto.setPassword("max");
        userDto.setEmail("max");
        userDto.setFirstName("max");
        userDto.setSurname("max");
        userDto.setPhoneNumber("+375999999999");
    }

    @Test
    public void givenTicketDto_whenSave_thenOk() throws Exception {

        userDto.setId(2);

        this.jsonBody = jsonMapper.toJson(userDto);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andDo(print()).andExpect(status().isOk());

    }

    @Test
    public void givenTicketId_whenRead_thenOk() throws Exception {

        this.jsonBody = jsonMapper.toJson(userDto);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/user/view/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void givenTicket_whenUpdate_thenOk() throws Exception {

        this.jsonBody = jsonMapper.toJson(userDto);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .put("/user/edit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void givenTicket_whenDelete_thenOk() throws Exception {

        this.jsonBody = jsonMapper.toJson(userDto);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("user/delete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andDo(print())
                .andExpect(status().isOk());

    }

}
