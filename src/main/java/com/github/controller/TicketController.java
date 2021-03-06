package com.github.controller;

import com.github.dto.TicketDto;
import com.github.metamodel.Roles;
import com.github.service.IItemsSecurityExpressions;
import com.github.service.ITicketService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("ticket-management")
public class TicketController {

    private final ITicketService iTicketService;

    private final IItemsSecurityExpressions iItemsSecurityExpressions;

    @PostMapping
    @Secured(Roles.USER)
    public ResponseEntity<TicketDto> createTicket(@RequestBody TicketDto ticketDto) {
        final String currentEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        TicketDto returnValue = iTicketService.createTicket(currentEmail, ticketDto);

        return ResponseEntity.ok(returnValue);
    }

    @GetMapping("{ticketId}")
    @PreAuthorize("@itemsSecurityExpressions.isUserOwnedTicket(#ticketId, authentication)")
    public ResponseEntity<TicketDto> readTicket(@PathVariable Long ticketId) {

        TicketDto returnValue = iTicketService.readTicket(ticketId);

        return ResponseEntity.ok(returnValue);
    }

    @PutMapping("{ticketId}")
    @PreAuthorize("@itemsSecurityExpressions.isUserOwnedTicket(#ticketId, authentication)")
    public ResponseEntity<TicketDto> updateTicket(@PathVariable Long ticketId, @RequestBody TicketDto ticketDto) {
        TicketDto returnValue = iTicketService.update(ticketId, ticketDto);

        return ResponseEntity.ok(returnValue);
    }

    @DeleteMapping("{ticketId}")
    @PreAuthorize("@itemsSecurityExpressions.isUserOwnedTicket(#ticketId, authentication)")
    public void deleteTicket(@PathVariable Long ticketId) {
        iTicketService.deleteTicket(ticketId);
    }

    @PreAuthorize("permitAll")
    @GetMapping("by-event/{eventId}")
    public ResponseEntity<Set<TicketDto>> getEventTickets(@PathVariable Long eventId) {
        return ResponseEntity.ok(iTicketService.getEventTickets(eventId));
    }

    @PreAuthorize("permitAll")
    @GetMapping("by-user/{userId}")
    public ResponseEntity<Set<TicketDto>> getUserTickets(@PathVariable Long userId) {
        return ResponseEntity.ok(iTicketService.getUserTickets(userId));
    }

}
