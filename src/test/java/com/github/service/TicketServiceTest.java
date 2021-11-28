package com.github.service;

import com.github.dao.TicketDao;
import com.github.dto.TicketDto;
import com.github.entity.Ticket;
import com.github.mapper.Mapper;
import com.github.service.TicketService;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

public class TicketServiceTest {

    @Mock
    private Mapper<TicketDto, Ticket> ticketMapper;

    @Mock
    private TicketService ticketService;

    @Mock
    private TicketDao ticketDao;

}
