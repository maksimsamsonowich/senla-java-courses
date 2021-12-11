package com.github.controller;

import com.github.dto.EventDto;
import com.github.metamodel.Roles;
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
    @Secured( Roles.ADMIN )
    public ResponseEntity<EventDto> createEvent(@RequestBody EventDto eventDto) {
        return ResponseEntity.ok(iEventService.createEvent(eventDto));
    }

    @GetMapping("{eventId}")
    public ResponseEntity<EventDto> readEvent(@PathVariable Long eventId) {
        return ResponseEntity.ok(iEventService.readEvent(eventId));
    }

    @PutMapping("{eventId}")
    @Secured({ Roles.ARTIST, Roles.ADMIN })
    public ResponseEntity<EventDto> updateEvent(@PathVariable Long eventId, @RequestBody EventDto eventDto) {
        return ResponseEntity.ok(iEventService.update(eventId, eventDto));
    }

    @DeleteMapping("{eventId}")
    @Secured({ Roles.ARTIST, Roles.ADMIN })
    public void deleteEvent(@PathVariable Long eventId) {
        iEventService.deleteEvent(eventId);
    }

    @GetMapping("by-location/{locationId}")
    public ResponseEntity<Set<EventDto>> getEventsByLocation(@PathVariable Long locationId) {
        return ResponseEntity.ok(iEventService.getEventsByLocation(locationId));
    }
}
