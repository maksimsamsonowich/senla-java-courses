package com.github.controller;

import com.github.dto.EventDto;
import com.github.dto.LocationDto;
import com.github.service.api.IEventService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("events")
public class EventController{

    private final IEventService iEventService;
    
    @PostMapping("register")
    public EventDto createEvent(@RequestBody EventDto eventDto) {
        return iEventService.createEvent(eventDto);
    }

    @GetMapping("view/{id}")
    public EventDto readEvent(@PathVariable("id") Integer id) {
        return iEventService.readEvent(id);
    }

    @PutMapping("edit/{id}")
    public EventDto updateEventDescription(@PathVariable("id") Integer id,
                                           @RequestBody EventDto eventDto) {
        return iEventService.update(eventDto);
    }

    @DeleteMapping("delete")
    public void deleteEvent(@RequestBody EventDto eventDto) {
        iEventService.deleteEvent(eventDto);
    }

    @GetMapping("location/{id}")
    public Set<EventDto> getEventsByLocation(@RequestBody LocationDto locationDto) {
        return iEventService.getEventsByLocation(locationDto);
    }
}
