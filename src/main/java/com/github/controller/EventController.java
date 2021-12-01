package com.github.controller;

import com.github.dto.EventDto;
import com.github.dto.LocationDto;
import com.github.service.api.IEventService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("event-management")
public class EventController{

    private final IEventService iEventService;
    
    @PostMapping("{eventId}")
    public EventDto createEvent(@PathVariable Integer eventId, @RequestBody EventDto eventDto) {
        return iEventService.createEvent(eventId, eventDto);
    }

    @GetMapping("{eventId}")
    public EventDto readEvent(@PathVariable Integer eventId) {
        return iEventService.readEvent(eventId);
    }

    @PutMapping("{eventId}")
    public EventDto updateEventDescription(@PathVariable Integer eventId, @RequestBody EventDto eventDto) {
        return iEventService.update(eventId, eventDto);
    }

    @DeleteMapping("{eventId}")
    public void deleteEvent(@PathVariable("eventId") Integer eventId) {
        iEventService.deleteEvent(eventId);
    }

    @GetMapping("by-location/{locationId}")
    public Set<EventDto> getEventsByLocation(@PathVariable Integer locationId) {
        return iEventService.getEventsByLocation(locationId);
    }
}
