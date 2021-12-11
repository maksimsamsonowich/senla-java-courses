package com.github.controller;

import com.github.dto.TicketDto;
import com.github.entity.Credential;
import com.github.metamodel.Roles;
import com.github.service.ITicketService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("ticket-management")
public class TicketController {

    private ITicketService iTicketService;

    @PostMapping
    @Secured( Roles.USER )
    public ResponseEntity<TicketDto> createTicket(@RequestBody TicketDto ticketDto) {
        String currentEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(iTicketService.createTicket(currentEmail, ticketDto));
    }

    @GetMapping("{ticketId}")
    @Secured( { Roles.USER, Roles.ADMIN })
    public ResponseEntity<TicketDto> readTicket(@PathVariable Long ticketId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String currentEmail = authentication.getName();
        boolean hasAdminRole = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));

        TicketDto ticketDto = iTicketService.readTicket(hasAdminRole, currentEmail, ticketId);

        return ResponseEntity.ok(ticketDto);
    }

    @Secured( Roles.ADMIN )
    @PutMapping("{ticketId}")
    public ResponseEntity<TicketDto> updateTicket(@PathVariable Long ticketId, @RequestBody TicketDto ticketDto) {
        return ResponseEntity.ok(iTicketService.update(ticketId, ticketDto));
    }

    @DeleteMapping("{ticketId}")
    @Secured( { Roles.USER, Roles.ADMIN })
    public void deleteTicket(@PathVariable Long ticketId) {
        iTicketService.deleteTicket(ticketId);
    }

    @GetMapping("by-event/{eventId}")
    public ResponseEntity<Set<TicketDto>> getEventTickets(@PathVariable Long eventId) {
        return ResponseEntity.ok(iTicketService.getEventTickets(eventId));
    }

    @GetMapping("by-user/{userId}")
    public ResponseEntity<Set<TicketDto>> getUserTickets(@PathVariable Long userId) {
        return ResponseEntity.ok(iTicketService.getUserTickets(userId));
    }

}
