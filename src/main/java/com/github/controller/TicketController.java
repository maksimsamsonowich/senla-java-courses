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

    @PostMapping("{id}")
    public TicketDto createLocation(@PathVariable Integer id, @RequestBody TicketDto ticketDto) {
        return iTicketService.createTicket(id, ticketDto);
    }

    @GetMapping("{id}")
    public TicketDto readLocation(@PathVariable Integer id) {
        return iTicketService.readTicket(id);
    }

    @PutMapping("{id}")
    public TicketDto updateLocation(@PathVariable Integer id, @RequestBody TicketDto ticketDto) {
        return iTicketService.update(id, ticketDto);
    }

    @DeleteMapping("{id}")
    public void deleteLocation(@PathVariable("id") Integer id) {
        iTicketService.deleteTicket(id);
    }

    @GetMapping("by-event")
    public Set<TicketDto> getEventTickets(@RequestBody TicketDto ticketDto) {
        return iTicketService.getEventTickets(ticketDto);
    }

    @GetMapping("by-user")
    public Set<TicketDto> getUserTickets(@RequestBody TicketDto ticketDto) {
        return iTicketService.getUserTickets(ticketDto);
    }

}
