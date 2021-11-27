package com.github.service.api;

import com.github.dto.EventDto;
import com.github.dto.TicketDto;
import com.github.dto.UserDto;

import java.util.Set;

public interface ITicketService {

    void createTicket(TicketDto ticketDto);

    TicketDto readTicket(Integer id);

    TicketDto update(TicketDto ticketDto);

    void deleteTicket(TicketDto ticketDto);

    Set<TicketDto>getEventTickets(Integer id);

    Set<TicketDto> getUserTickets(Integer id);

}
