package com.github.service.api;

import com.github.dto.TicketDto;

import java.util.Set;

public interface ITicketService {

    TicketDto createTicket(Integer id, TicketDto ticketDto);

    TicketDto readTicket(Integer id);

    TicketDto update(Integer id, TicketDto ticketDto);

    void deleteTicket(Integer id);

    Set<TicketDto>getEventTickets(Integer id);

    Set<TicketDto> getUserTickets(Integer id);

}
