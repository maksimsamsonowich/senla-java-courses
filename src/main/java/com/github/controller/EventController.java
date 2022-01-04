package com.github.controller;

import com.github.dto.EventDto;
import com.github.filter.PaginationDto;
import com.github.metamodel.Roles;
import com.github.service.IEventService;
import com.github.service.IItemsSecurityExpressions;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("event-management")
public class EventController{

    private final IEventService iEventService;

    private final IItemsSecurityExpressions iItemsSecurityExpressions;

    @PostMapping
    @Secured(Roles.ARTIST)
    public ResponseEntity<EventDto> createEvent(@RequestBody EventDto eventDto) {
        log.info("Event controller received the post request (createEvent).");

        return ResponseEntity.ok(iEventService.createEvent(eventDto));
    }

    @GetMapping("{eventId}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<EventDto> readEvent(@PathVariable Long eventId) {
        log.info("Event controller received the get request (readEvent).");

        return ResponseEntity.ok(iEventService.readEvent(eventId));
    }

    @Secured(Roles.ARTIST)
    @PutMapping("{eventId}")
    @PreAuthorize("@itemsSecurityExpressions.isUserOwnedEvent(#eventId, authentication)")
    public ResponseEntity<EventDto> updateEvent(@PathVariable Long eventId, @RequestBody EventDto eventDto) {
        log.info("Event controller received the put request (updateEvent).");

        return ResponseEntity.ok(iEventService.update(eventId, eventDto));
    }

    @Secured(Roles.ARTIST)
    @DeleteMapping("{eventId}")
    @PreAuthorize("@itemsSecurityExpressions.isUserOwnedEvent(#eventId, authentication)")
    public void deleteEvent(@PathVariable Long eventId) {
        log.info("Event controller received the delete request (deleteEvent).");

        iEventService.deleteEvent(eventId);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("by-location/{locationId}")
    public ResponseEntity<Set<EventDto>> getEventsByLocation(@PathVariable Long locationId) {
        log.info("Event controller received the get request (getEventsByLocation).");

        return ResponseEntity.ok(iEventService.getEventsByLocation(locationId));
    }

    @GetMapping("events")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Set<EventDto>> getAllEventsSorted(@RequestBody PaginationDto paginationDto) {
        log.info("Event controller received the get request (getAllEventsSorted).");

        return ResponseEntity.ok(iEventService.getAllEvents(paginationDto));
    }

}
