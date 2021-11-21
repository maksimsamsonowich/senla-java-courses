package com.github.controller;

import com.github.dto.EventDto;
import com.github.service.api.IEventService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

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
}
