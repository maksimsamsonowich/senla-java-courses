package com.github.controller;

import com.github.WebAppInitializer;
import com.github.configs.root.ApplicationConfig;
import com.github.configs.root.DatabaseConfig;
import com.github.dto.TicketDto;
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

import java.sql.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ApplicationConfig.class, WebAppInitializer.class, DatabaseConfig.class })
public class TicketControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private JsonMapper jsonMapper;

    private TicketDto ticketDto;

    private String jsonBody;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();

        ticketDto = new TicketDto();
        ticketDto.setId(1);
        ticketDto.setOrderDate(Date.valueOf("2020-12-1"));
    }

    @Test
    public void givenTicketDto_whenSave_thenOk() throws Exception {

        ticketDto.setId(2);

        this.jsonBody = jsonMapper.toJson(ticketDto);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/ticket/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andDo(print()).andExpect(status().isOk());

    }

    @Test
    public void givenTicketId_whenRead_thenOk() throws Exception {

        this.jsonBody = jsonMapper.toJson(ticketDto);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/ticket/view/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void givenTicket_whenUpdate_thenOk() throws Exception {

        this.jsonBody = jsonMapper.toJson(ticketDto);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .put("/ticket/edit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void givenTicket_whenDelete_thenOk() throws Exception {

        this.jsonBody = jsonMapper.toJson(ticketDto);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("ticket/delete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void givenEvent_whenTickets_thenOk() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/ticket/event/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void givenUser_whenTickets_thenOk() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/ticket/user/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk());

    }

}
