package com.github.controller;

import com.github.dto.EventDto;
import com.github.dto.LocationDto;
import com.github.entity.Event;
import com.github.service.api.IEventService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Component
@RestController
@AllArgsConstructor
@RequestMapping("/events")
public class EventController{

    private final IEventService iEventService;

    @PostMapping("/register/")
    public void createEvent(@RequestBody EventDto eventDto) {
        iEventService.createEvent(eventDto);
    }

    @GetMapping("/view/{id}")
    public EventDto readEvent(@PathVariable("id") Integer id,
                              @RequestBody EventDto eventDto) {
        return iEventService.readEvent(eventDto);
    }

    @PutMapping("/edit/{id}")
    public EventDto updateEventDescription(@PathVariable("id") Integer id,
                                           @RequestBody EventDto eventDto) {
        return iEventService.update(eventDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEvent(@PathVariable("id") Integer id,
                            @RequestBody EventDto eventDto) {
        iEventService.deleteEvent(eventDto);
    }

    @GetMapping("/location/{id}")
    public Set<EventDto> getEventsByLocation(@PathVariable("id") Integer id,
                                             @RequestBody LocationDto locationDto) {
        return iEventService.getEventsByLocation(locationDto);
    }
}
