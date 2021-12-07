package com.github.controller;

import com.github.dto.TicketDto;
import com.github.service.ITicketService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("ticket-management")
public class TicketController {

    private ITicketService iTicketService;

    @PostMapping
    public ResponseEntity<TicketDto> createTicket(@RequestBody TicketDto ticketDto) {
        return ResponseEntity.ok(iTicketService.createTicket(ticketDto));
    }

    @GetMapping("{ticketId}")
    public ResponseEntity<TicketDto> readTicket(@PathVariable Integer ticketId) {
        return ResponseEntity.ok(iTicketService.readTicket(ticketId));
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("{ticketId}")
    public ResponseEntity<TicketDto> updateTicket(@PathVariable Integer ticketId, @RequestBody TicketDto ticketDto) {
        return ResponseEntity.ok(iTicketService.update(ticketId, ticketDto));
    }

    @DeleteMapping("{ticketId}")
    public void deleteTicket(@PathVariable Integer ticketId) {
        iTicketService.deleteTicket(ticketId);
    }

    @GetMapping("by-event/{eventId}")
    public ResponseEntity<Set<TicketDto>> getEventTickets(@PathVariable Integer eventId) {
        return ResponseEntity.ok(iTicketService.getEventTickets(eventId));
    }

    @GetMapping("by-user/{userId}")
    public ResponseEntity<Set<TicketDto>> getUserTickets(@PathVariable Integer userId) {
        return ResponseEntity.ok(iTicketService.getUserTickets(userId));
    }

}
