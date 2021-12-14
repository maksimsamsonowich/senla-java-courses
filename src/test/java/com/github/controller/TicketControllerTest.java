package com.github.controller;

import com.github.WebAppInitializer;
import com.github.configs.root.ApplicationConfig;
import com.github.configs.root.DatabaseConfig;
import com.github.dto.EventDto;
import com.github.dto.TicketDto;
import com.github.mapper.impl.JsonMapper;
import com.github.service.ITicketService;
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

import java.sql.Date;

@Transactional
@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfig.class, WebAppInitializer.class, DatabaseConfig.class })
public class TicketControllerTest {

    private MockMvc mockMvc;

    private String jsonBody;

    private EventDto eventDto;

    private TicketDto ticketDto;

    @Autowired
    private JsonMapper jsonMapper;

    @Autowired
    private ITicketService ticketService;

    @Autowired
    private TicketController ticketController;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(ticketController).build();

        ticketDto = new TicketDto()
                .setOrderDate(Date.valueOf("2019-01-26"))
                .setEventHolding(new EventDto()
                        .setId(1L));

        eventDto = new EventDto()
                .setId(1L);
    }

    @Test
    @WithMockUser(username = "fightingdemons@gmail.com", roles = "USER")
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
    @WithMockUser(username = "fightingdemons@gmail.com", roles = "ADMIN")
    public void readTicketSuccess() throws Exception {

        ticketDto = ticketService.createTicket("fightingdemons@gmail.com", ticketDto);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/ticket-management/{ticketId}", ticketDto.getId()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id",
                        CoreMatchers.is(Integer.parseInt(ticketDto.getId().toString()))));

    }

    @Test
    @WithMockUser(username = "fightingdemons@gmail.com", roles = "ADMIN")
    public void updateTicketSuccess() throws Exception {

        ticketDto = ticketService.createTicket("fightingdemons@gmail.com", ticketDto);
        this.jsonBody = jsonMapper.toJson(ticketDto);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .put("/ticket-management/{ticketId}", ticketDto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id",
                        CoreMatchers.is(Integer.parseInt(ticketDto.getId().toString()))));

    }

    @Test
    @WithMockUser(username = "fightingdemons@gmail.com", roles = "USER")
    public void deleteTicketSuccess() throws Exception {

        ticketDto = ticketService.createTicket("fightingdemons@gmail.com", ticketDto);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/ticket-management/{ticketId}", ticketDto.getId()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        Assert.assertNull(ticketService.readTicket(ticketDto.getId()));

    }


}
