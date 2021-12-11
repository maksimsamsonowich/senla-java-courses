package com.github.service;

import com.github.dto.TicketDto;

import java.util.Set;

public interface ITicketService {

    TicketDto createTicket(String email, TicketDto ticketDto);

    TicketDto readTicket(Boolean hasAdminRole, String email, Long id);

    TicketDto update(Long id, TicketDto ticketDto);

    void deleteTicket(Long id);

    Set<TicketDto>getEventTickets(Long id);

    Set<TicketDto> getUserTickets(Long id);

}
