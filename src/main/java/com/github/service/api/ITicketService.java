package com.github.service.api;

import com.github.dto.TicketDto;

import java.util.Set;

public interface ITicketService {

    TicketDto createTicket(TicketDto ticketDto);

    TicketDto readTicket(Integer id);

    TicketDto update(TicketDto ticketDto);

    void deleteTicket(TicketDto ticketDto);

    Set<TicketDto>getEventTickets(Integer id);

    Set<TicketDto> getUserTickets(Integer id);

}
