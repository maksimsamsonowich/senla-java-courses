package com.github.controller;

import com.github.dto.TicketDto;
import com.github.service.api.ITicketService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("ticket-management")
public class TicketController {

    private ITicketService iTicketService;

    @PostMapping("{ticketId}")
    public TicketDto createLocation(@PathVariable Integer ticketId, @RequestBody TicketDto ticketDto) {
        return iTicketService.createTicket(ticketId, ticketDto);
    }

    @GetMapping("{ticketId}")
    public TicketDto readLocation(@PathVariable Integer ticketId) {
        return iTicketService.readTicket(ticketId);
    }

    @PutMapping("{ticketId}")
    public TicketDto updateLocation(@PathVariable Integer ticketId, @RequestBody TicketDto ticketDto) {
        return iTicketService.update(ticketId, ticketDto);
    }

    @DeleteMapping("{ticketId}")
    public void deleteLocation(@PathVariable Integer ticketId) {
        iTicketService.deleteTicket(ticketId);
    }

    @GetMapping("by-event/{eventId}")
    public Set<TicketDto> getEventTickets(@PathVariable Integer eventId) {
        return iTicketService.getEventTickets(eventId);
    }

    @GetMapping("by-user/{userId}")
    public Set<TicketDto> getUserTickets(@PathVariable Integer userId) {
        return iTicketService.getUserTickets(userId);
    }

}
