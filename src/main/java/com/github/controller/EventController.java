package com.github.controller;

import com.github.dto.EventDto;
import com.github.service.IEventService;
import org.springframework.stereotype.Component;

@Component
public class EventController implements IEventController {

    private final IEventService iEventService;

    public EventController(IEventService iEventService) {
        this.iEventService = iEventService;
    }

    @Override
    public void createEvent(EventDto eventDto) {
        iEventService.createEvent(eventDto);
    }

    @Override
    public EventDto readEvent(EventDto eventDto) {
        return iEventService.readEvent(eventDto);
    }

    @Override
    public EventDto updateEventDescription(EventDto eventDto, String newDescription) {
        return iEventService.updateEventDescription(eventDto, newDescription);
    }

    @Override
    public void deleteEvent(EventDto eventDto) {
        iEventService.deleteEvent(eventDto);
    }
}
