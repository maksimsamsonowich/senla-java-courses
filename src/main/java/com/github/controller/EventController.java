package com.github.controller;

import com.github.dto.EventDto;
import com.github.service.IEventService;
import org.springframework.stereotype.Component;

@Component
public class EventController{

    private final IEventService iEventService;

    public EventController(IEventService iEventService) {
        this.iEventService = iEventService;
    }

    public void createEvent(EventDto eventDto) {
        iEventService.createEvent(eventDto);
    }

    public EventDto readEvent(EventDto eventDto) {
        return iEventService.readEvent(eventDto);
    }

    public EventDto updateEventDescription(EventDto eventDto, String newDescription) {
        return iEventService.updateEventDescription(eventDto, newDescription);
    }

    public void deleteEvent(EventDto eventDto) {
        iEventService.deleteEvent(eventDto);
    }
}
