package com.github.controller;

import com.github.dto.EventDto;
import com.github.dto.LocationDto;
import com.github.entity.Event;
import com.github.service.api.IEventService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@AllArgsConstructor
public class EventController{

    private final IEventService iEventService;

    public void createEvent(EventDto eventDto) {
        iEventService.createEvent(eventDto);
    }

    public EventDto readEvent(EventDto eventDto) {
        return iEventService.readEvent(eventDto);
    }

    public EventDto updateEventDescription(EventDto eventDto) {
        return iEventService.update(eventDto);
    }

    public void deleteEvent(EventDto eventDto) {
        iEventService.deleteEvent(eventDto);
    }

    public Set<EventDto> getEventsByLocation(LocationDto locationDto) {
        return iEventService.getEventsByLocation(locationDto);
    }
}
