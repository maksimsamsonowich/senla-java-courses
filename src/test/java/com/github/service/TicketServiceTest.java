package com.github.service;

import com.github.dao.TicketDao;
import com.github.dto.TicketDto;
import com.github.entity.Ticket;
import com.github.mapper.Mapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class TicketServiceTest {

    @Mock
    private Mapper<TicketDto, Ticket> ticketMapper;

    @Mock
    private TicketService ticketService;

    @Mock
    private TicketDao ticketDao;

    @Test
    public void createTicketTest() {
        Ticket ticketEntityMock = new Ticket();
        Mockito.when(ticketDao.create(ticketEntityMock)).thenReturn(ticketEntityMock);
        TicketDto ticketDtoMock = ticketMapper.toDto(ticketEntityMock, TicketDto.class);

        TicketDto ticketDto = ticketService.createTicket(ticketDtoMock.getId(), ticketDtoMock);

        Assert.assertEquals(ticketDto, ticketDtoMock);
    }

    @Test
    public void readTicketTest() {
        final int testId = 1;

        Ticket ticketEntityMock = new Ticket();
        Mockito.when(ticketDao.read(testId)).thenReturn(ticketEntityMock);
        TicketDto ticketDtoMock = ticketMapper.toDto(ticketEntityMock, TicketDto.class);

        TicketDto ticketDto = ticketService.readTicket(testId);

        Assert.assertEquals(ticketDto, ticketDtoMock);

    }

    @Test
    public void updateTicketTest() {
        Ticket ticketEntityMock = new Ticket();
        Mockito.when(ticketDao.update(ticketEntityMock)).thenReturn(ticketEntityMock);
        TicketDto ticketDtoMock = ticketMapper.toDto(ticketEntityMock, TicketDto.class);

        TicketDto ticketDto = ticketService.update(ticketDtoMock.getId(), ticketDtoMock);

        Assert.assertEquals(ticketDto, ticketDtoMock);
    }

    @Test
    public void deleteTicketTest() {
        TicketDto ticketDtoMock = new TicketDto();
        Ticket ticketEntityMock = new Ticket();

        doNothing().when(ticketDao).delete(ticketEntityMock);

        ticketService.deleteTicket(ticketDtoMock.getId());

        verify(ticketDao, times(0)).delete(ticketEntityMock);
    }

    @Test
    public void getEventTicketsTest() {
        final int testId = 1;

        TicketDto ticketDto = new TicketDto();
        ticketDto.setId(testId);

        Set<Ticket> ticketSetEntityMock = new HashSet<>();
        Mockito.when(ticketDao.getEventTickets(testId)).thenReturn(ticketSetEntityMock);
        Set<TicketDto> ticketDtoSetMock = ticketMapper.setToDto(ticketSetEntityMock, TicketDto.class);

        Set<TicketDto> ticketDtoSet = ticketService.getEventTickets(ticketDto);

        Assert.assertEquals(ticketDtoSet, ticketDtoSetMock);
    }

    @Test
    public void getUserTicketsTest() {
        final int testId = 1;

        TicketDto ticketDto = new TicketDto();
        ticketDto.setId(testId);

        Set<Ticket> ticketSetEntityMock = new HashSet<>();
        Mockito.when(ticketDao.getTicketsByUser(testId)).thenReturn(ticketSetEntityMock);
        Set<TicketDto> ticketDtoSetMock = ticketMapper.setToDto(ticketSetEntityMock, TicketDto.class);

        Set<TicketDto> ticketDtoSet = ticketService.getUserTickets(ticketDto);

        Assert.assertEquals(ticketDtoSet, ticketDtoSetMock);
    }

}
