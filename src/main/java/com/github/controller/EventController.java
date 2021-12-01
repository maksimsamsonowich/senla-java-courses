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
    
    @PostMapping("{id}")
    public EventDto createEvent(@PathVariable Integer id, @RequestBody EventDto eventDto) {
        return iEventService.createEvent(id, eventDto);
    }

    @GetMapping("{id}")
    public EventDto readEvent(@PathVariable("id") Integer id) {
        return iEventService.readEvent(id);
    }

    @PutMapping("{id}")
    public EventDto updateEventDescription(@PathVariable Integer id, @RequestBody EventDto eventDto) {
        return iEventService.update(id, eventDto);
    }

    @DeleteMapping("{id}")
    public void deleteEvent(@PathVariable("id") Integer id) {
        iEventService.deleteEvent(id);
    }

    @GetMapping("by-location")
    public Set<EventDto> getEventsByLocation(@RequestBody LocationDto locationDto) {
        return iEventService.getEventsByLocation(locationDto);
    }
}
