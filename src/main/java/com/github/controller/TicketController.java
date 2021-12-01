package com.github.controller;

import com.github.dto.TicketDto;
import com.github.service.api.ITicketService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("ticket")
public class TicketController {

    private ITicketService iTicketService;

    @PostMapping("register")
    public TicketDto createLocation(@RequestBody TicketDto ticketDto) {
        return iTicketService.createTicket(ticketDto);
    }

    @GetMapping("view/{id}")
    public TicketDto readLocation(@PathVariable Integer id) {
        return iTicketService.readTicket(id);
    }

    @PutMapping("update")
    public TicketDto updateLocation(@RequestBody TicketDto ticketDto) {
        return iTicketService.update(ticketDto);
    }

    @DeleteMapping("delete")
    public void deleteLocation(@RequestBody TicketDto ticketDto) {
        iTicketService.deleteTicket(ticketDto);
    }

    @GetMapping("event/{id}")
    public Set<TicketDto> getEventTickets(@PathVariable Integer id) {
        return iTicketService.getEventTickets(id);
    }

    @GetMapping("user/{id}")
    public Set<TicketDto> getUserTickets(@PathVariable Integer id) {
        return iTicketService.getUserTickets(id);
    }

}
