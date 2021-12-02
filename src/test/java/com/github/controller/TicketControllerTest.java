package com.github.controller;

import com.github.WebAppInitializer;
import com.github.configs.root.ApplicationConfig;
import com.github.configs.root.DatabaseConfig;
import com.github.dao.TicketDao;
import com.github.dto.TicketDto;
import com.github.entity.Ticket;
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
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Set;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfig.class, WebAppInitializer.class, DatabaseConfig.class })
public class TicketControllerTest {

    @Autowired
    private JsonMapper jsonMapper;

    @Autowired
    private TicketController ticketController;

    @Autowired
    private TicketDao ticketDao;

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
    public void givenTicketDto_whenSave_thenOk() throws Exception {

        this.jsonBody = jsonMapper.toJson(ticketDto);

        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/ticket-management")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn();

        int jsonIdResult = JsonPath.read(result.getResponse().getContentAsString(), "$.id");
        String jsonOrderDateResult = JsonPath.read(result.getResponse().getContentAsString(), "$.orderDate").toString();

        Assert.assertEquals(ticketDao.read(jsonIdResult).getId(), jsonIdResult);
        Assert.assertEquals(ticketController.readTicket(jsonIdResult).getOrderDate().toString(), jsonOrderDateResult);

    }

    @Test
    @Transactional
    public void givenTicketId_whenRead_thenOk() throws Exception {

        ticketDto.setId(54);
        ticketDto = ticketController.createTicket(ticketDto);

        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/ticket-management/{ticketId}", ticketDto.getId()))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn();

        int jsonIdResult = JsonPath.read(result.getResponse().getContentAsString(), "$.id");

        TicketDto ticket = ticketController.readTicket(ticketDto.getId());

        Assert.assertEquals(ticket.getId(), jsonIdResult);

    }

    @Test
    public void givenTicket_whenUpdate_thenOk() throws Exception {

        ticketDto.setId(5);
        this.jsonBody = jsonMapper.toJson(ticketDto);

        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders
                        .put("/ticket-management/{ticketId}", ticketDto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn();

        int jsonIdResult = JsonPath.read(result.getResponse().getContentAsString(), "$.id");

        Assert.assertEquals(ticketDto.getId(), jsonIdResult);
    }

    @Test
    public void givenTicket_whenDelete_thenOk() throws Exception {

        ticketController.createTicket(ticketDto);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/ticket-management/{ticketId}", ticketDto.getId()))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void givenEvent_whenTickets_thenOk() throws Exception {

        this.jsonBody = jsonMapper.toJson(ticketDto);

        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/ticket-management/by-event/{eventId}", 1))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn();

        Set<Ticket> tickets = ticketDao.getTicketsByUser(1);

        int iterator = 0;

        for(Ticket ticket : tickets) {
            int jsonEventIdActual = JsonPath.read(result.getResponse().getContentAsString(), "$.[" + iterator + "].id");

            Assert.assertEquals(ticket.getId(), jsonEventIdActual);

            iterator++;
        }

    }

}
