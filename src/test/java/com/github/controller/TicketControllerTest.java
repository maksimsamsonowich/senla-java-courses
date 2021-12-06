package com.github.controller;

import com.github.WebAppInitializer;
import com.github.configs.root.ApplicationConfig;
import com.github.configs.root.DatabaseConfig;
import com.github.dto.TicketDto;
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

import java.sql.Date;

@Transactional
@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfig.class, WebAppInitializer.class, DatabaseConfig.class })
public class TicketControllerTest {

    @Autowired
    private JsonMapper jsonMapper;

    @Autowired
    private TicketController ticketController;

    private TicketDto ticketDto;

    private String jsonBody;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(ticketController).build();

        ticketDto = new TicketDto();
        ticketDto.setOrderDate(Date.valueOf("2019-01-26"));
    }

    @Test
    public void createTicketSuccess() throws Exception {

        this.jsonBody = jsonMapper.toJson(ticketDto);
        ticketDto.setOrderDate(Date.valueOf("2019-01-25"));

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/ticket-management")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.orderDate",
                                CoreMatchers.is(ticketDto.getOrderDate().toString())));

    }

    @Test
    @Transactional(readOnly = true)
    public void readTicketSuccess() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/ticket-management/{ticketId}", 1))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.orderDate",
                                CoreMatchers.is(ticketDto.getOrderDate().toString())));

    }

    @Test
    public void updateTicketSuccess() throws Exception {

        ticketDto.setId(5);
        this.jsonBody = jsonMapper.toJson(ticketDto);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .put("/ticket-management/{ticketId}", ticketDto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.id",
                                CoreMatchers.is(ticketDto.getId())));

    }

    @Test
    public void deleteTicketSuccess() throws Exception {

        ticketDto = ticketController.createTicket(ticketDto);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/ticket-management/{ticketId}", ticketDto.getId()))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @Transactional(readOnly = true)
    public void getEventTicketsSuccess() throws Exception {

        this.jsonBody = jsonMapper.toJson(ticketDto);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/ticket-management/by-event/{eventId}", 1))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.[0].orderDate",
                                CoreMatchers.is(ticketDto.getOrderDate().toString())));

    }

}
