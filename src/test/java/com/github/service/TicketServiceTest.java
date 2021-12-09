package com.github.service;

import com.github.dao.TicketDao;
import com.github.dto.TicketDto;
import com.github.entity.Ticket;
import com.github.mapper.Mapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class TicketServiceTest {

    private Ticket ticketMock;

    @Mock
    private TicketDao ticketDao;

    @InjectMocks
    private TicketService ticketService;

    @Mock
    private Mapper<TicketDto, Ticket> ticketMapper;

    @Before
    public void setup() {
        ticketMock = new Ticket()
                .setOrderDate(Date.valueOf("2021-12-03"));
    }

    @Test
    public void createTicketSuccess() {

        Mockito.when(ticketDao.create(ticketMock)).thenReturn(ticketMock);

        TicketDto expectedResult = ticketMapper.toDto(ticketMock, TicketDto.class);
        TicketDto actualResult = ticketService.createTicket(expectedResult);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void readTicketSuccess() {

        Mockito.when(ticketDao.read(ticketMock.getId())).thenReturn(ticketMock);

        TicketDto expectedResult = ticketMapper.toDto(ticketMock, TicketDto.class);
        TicketDto actualResult = ticketService.readTicket(ticketMock.getId());

        Assert.assertEquals(expectedResult, actualResult);

    }

    @Test
    public void updateTicketSuccess() {

        Mockito.when(ticketDao.update(ticketMock)).thenReturn(ticketMock);

        TicketDto expectedResult = new TicketDto();
        TicketDto actualResult = ticketService.update(ticketMock.getId(), expectedResult);

        Assert.assertNull(actualResult);
    }

    @Test
    public void deleteTicketSuccess() {

        doNothing().when(ticketDao).delete(ticketMock);
        Mockito.when(ticketDao.read(ticketMock.getId())).thenReturn(ticketMock);

        ticketService.deleteTicket(ticketMock.getId());

        verify(ticketDao, times(1)).delete(ticketMock);
    }

    @Test
    public void getEventTicketsSuccess() {

        Set<Ticket> ticketSetEntityMock = new HashSet<>();

        Mockito.when(ticketDao.getEventTickets(ticketMock.getId())).thenReturn(ticketSetEntityMock);

        Set<TicketDto> expectedResult = ticketMapper.setToDto(ticketSetEntityMock, TicketDto.class);
        Set<TicketDto> actualResult = ticketService.getEventTickets(ticketMock.getId());

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void getUserTicketsSuccess() {
        Set<Ticket> ticketSetEntityMock = new HashSet<>();

        Mockito.when(ticketDao.getTicketsByUser(ticketMock.getId())).thenReturn(ticketSetEntityMock);

        Set<TicketDto> expectedResult = ticketMapper.setToDto(ticketSetEntityMock, TicketDto.class);
        Set<TicketDto> actualResult = ticketService.getUserTickets(ticketMock.getId());

        Assert.assertEquals(actualResult, expectedResult);
    }

}
