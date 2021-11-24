package com.github.controller;

import com.github.dto.EventDto;
import com.github.dto.TicketDto;
import com.github.dto.UserDto;
import com.github.service.api.ITicketService;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class TicketController {

    private ITicketService iTicketService;

    public TicketController(ITicketService iTicketService) {
        this.iTicketService = iTicketService;
    }

    public void createLocation(TicketDto ticketDto) {
        iTicketService.createTicket(ticketDto);
    }

    public TicketDto readLocation(TicketDto ticketDto) {
        return iTicketService.readTicket(ticketDto);
    }

    public TicketDto updateLocation(TicketDto ticketDto) {
        return iTicketService.update(ticketDto);
    }

    public void deleteLocation(TicketDto ticketDto) {
        iTicketService.deleteTicket(ticketDto);
    }

    public Set<TicketDto> getEventTickets(EventDto eventDto) {
        return iTicketService.getEventTickets(eventDto);
    }

    public Set<TicketDto> getUserTickets(UserDto userDto) {
        return iTicketService.getUserTickets(userDto);
    }

}
