package com.github.controller;

import com.github.dto.EventDto;
import com.github.service.IEventService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("event-management")
public class EventController{

    private final IEventService iEventService;

    @PostMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<EventDto> createEvent(@RequestBody EventDto eventDto) {
        return ResponseEntity.ok(iEventService.createEvent(eventDto));
    }

    @GetMapping("{eventId}")
    public ResponseEntity<EventDto> readEvent(@PathVariable Integer eventId) {
        return ResponseEntity.ok(iEventService.readEvent(eventId));
    }

    @PutMapping("{eventId}")
    @Secured({"ROLE_ARTIST", "ROLE_ADMIN"})
    public ResponseEntity<EventDto> updateEvent(@PathVariable Integer eventId, @RequestBody EventDto eventDto) {
        return ResponseEntity.ok(iEventService.update(eventId, eventDto));
    }

    @DeleteMapping("{eventId}")
    @Secured({"ROLE_ARTIST", "ROLE_ADMIN"})
    public void deleteEvent(@PathVariable("eventId") Integer eventId) {
        iEventService.deleteEvent(eventId);
    }

    @GetMapping("by-location/{locationId}")
    public ResponseEntity<Set<EventDto>> getEventsByLocation(@PathVariable Integer locationId) {
        return ResponseEntity.ok(iEventService.getEventsByLocation(locationId));
    }
}
