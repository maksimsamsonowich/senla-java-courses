package com.github.dao;

import com.github.configs.root.DatabaseConfig;
import com.github.entity.Event;
import com.github.entity.Ticket;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebAppConfiguration
@RunWith(SpringRunner.class)
@ContextConfiguration(
        classes = { DatabaseConfig.class },
        loader = AnnotationConfigContextLoader.class)
@Transactional
public class TicketDaoTest {

    @Resource
    private TicketDao ticketDao;

    @Mock
    private Ticket testTicketEntity;

    @Before
    public void getTestEntity() {
        testTicketEntity = new Ticket()
                .setOrderDate(Date.valueOf("2020-12-1"));
    }

    @Test
    public void createTicketSuccess() {
        testTicketEntity = ticketDao.create(testTicketEntity);

        Ticket secondTicket = ticketDao.read(testTicketEntity.getId());

        Assert.assertEquals(testTicketEntity, secondTicket);
    }

    @Test
    public void deleteTicketSuccess() {
        testTicketEntity = ticketDao.create(testTicketEntity);

        ticketDao.delete(testTicketEntity);

        Ticket secondTicket = ticketDao.read(testTicketEntity.getId());
        Assert.assertNull(secondTicket);
    }

    @Test
    public void readTicketSuccess() {
        testTicketEntity = ticketDao.create(testTicketEntity);

        Ticket secondTicket = ticketDao.read(testTicketEntity.getId());

        Assert.assertEquals(secondTicket, testTicketEntity);
    }

    @Test
    public void updateTicketSuccess() {
        testTicketEntity = ticketDao.create(testTicketEntity);

        testTicketEntity.setOrderDate(Date.valueOf("2021-11-12"));
        ticketDao.update(testTicketEntity);

        Ticket secondTicket = ticketDao.read(testTicketEntity.getId());

        Assert.assertEquals(secondTicket, testTicketEntity);
    }

    @Test
    public void getEventTicketsTicketSuccess() {
        Set<Ticket> tickets = ticketDao.getEventTickets(1);
        Set<Ticket> ticketsSetMock = new HashSet<>();

        ticketsSetMock.add(ticketDao.read(1));

        Assert.assertEquals(tickets, ticketsSetMock);
    }

    @Test
    public void getTicketsByUserTicketSuccess() {
        Set<Ticket> tickets = ticketDao.getTicketsByUser(1);
        Set<Ticket> ticketsSetMock = new HashSet<>();

        ticketsSetMock.add(ticketDao.read(1));

        Assert.assertEquals(tickets, ticketsSetMock);
    }

    @Test
    public void getAllTicketsSuccess() {
        List<Ticket> actualResult = ticketDao.getAll();

        int step = 1;
        List<Ticket> expectedResult = new ArrayList<>();

        while (true) {
            Ticket ticket = ticketDao.read(step);

            if (ticket == null) {
                break;
            }

            expectedResult.add(ticket);
            step += 1;
        }

        Assert.assertEquals(expectedResult, actualResult);
    }

}
