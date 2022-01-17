package com.github.repository;

import com.github.configs.root.DatabaseConfig;
import com.github.entity.Event;
import com.github.entity.Ticket;
import com.github.entity.User;
import com.github.filter.EventFilterDto;
import com.github.repository.impl.TicketRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { DatabaseConfig.class })
@Transactional
public class TicketDaoTest {

    @Mock
    private Ticket testTicketEntity;

    @Resource
    private TicketRepository ticketDao;

    @Before
    public void getTestEntity() {
        testTicketEntity = new Ticket()
                .setOrderDate(Date.valueOf("2020-12-1"))
                .setOwner(new User()
                        .setId(1L))
                .setEventHolding(new Event()
                        .setId(1L));
    }

    @Test
    public void createTicketSuccess() {
        testTicketEntity = ticketDao.create(testTicketEntity);

        Ticket secondTicket = ticketDao.readById(testTicketEntity.getId());

        Assert.assertEquals(testTicketEntity, secondTicket);
    }

    @Test
    public void deleteTicketSuccess() {
        testTicketEntity = ticketDao.create(testTicketEntity);

        ticketDao.deleteById(testTicketEntity.getId());

        Ticket secondTicket = ticketDao.readById(testTicketEntity.getId());
        Assert.assertNull(secondTicket);
    }

    @Test
    public void readTicketSuccess() {
        testTicketEntity = ticketDao.create(testTicketEntity);

        Ticket secondTicket = ticketDao.readById(testTicketEntity.getId());

        Assert.assertEquals(secondTicket, testTicketEntity);
    }

    @Test
    public void updateTicketSuccess() {
        testTicketEntity = ticketDao.create(testTicketEntity);

        testTicketEntity.setOrderDate(Date.valueOf("2021-11-12"));
        ticketDao.update(testTicketEntity);

        Ticket secondTicket = ticketDao.readById(testTicketEntity.getId());

        Assert.assertEquals(secondTicket, testTicketEntity);
    }

    @Test
    public void getEventTicketsTicketSuccess() {
        Set<Ticket> tickets = ticketDao.getEventTickets(1L);
        Set<Ticket> ticketsSetMock = new HashSet<>();

        ticketsSetMock.add(ticketDao.readById(1L));

        Assert.assertEquals(tickets, ticketsSetMock);
    }

    @Test
    public void getTicketsByUserTicketSuccess() {
        Set<Ticket> tickets = ticketDao.getTicketsByUser(1L);
        Set<Ticket> ticketsSetMock = new HashSet<>();

        ticketsSetMock.add(ticketDao.readById(1L));

        Assert.assertEquals(tickets, ticketsSetMock);
    }

    @Test
    public void getAllTicketsSuccess() {
        List<Ticket> actualResult = ticketDao.getAll(new EventFilterDto());

        long step = 1;
        List<Ticket> expectedResult = new ArrayList<>();

        while (true) {
            Ticket ticket = ticketDao.readById(step);

            if (ticket == null) {
                break;
            }

            expectedResult.add(ticket);
            step += 1;
        }

        Assert.assertEquals(expectedResult, actualResult);
    }

}
