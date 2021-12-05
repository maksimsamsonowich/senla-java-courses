package com.github.controller;

import com.github.dto.TicketDto;
import com.github.service.ITicketService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("ticket-management")
public class TicketController {

    private ITicketService iTicketService;

    @PostMapping
    public TicketDto createTicket(@RequestBody TicketDto ticketDto) {
        return iTicketService.createTicket(ticketDto);
    }

    @GetMapping("{ticketId}")
    public TicketDto readTicket(@PathVariable Integer ticketId) {
        return iTicketService.readTicket(ticketId);
    }

    @PutMapping("{ticketId}")
    public TicketDto updateTicket(@PathVariable Integer ticketId, @RequestBody TicketDto ticketDto) {
        return iTicketService.update(ticketId, ticketDto);
    }

    @DeleteMapping("{ticketId}")
    public void deleteTicket(@PathVariable Integer ticketId) {
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
