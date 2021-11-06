package com.github.controller;

import com.github.dto.EventDto;

public interface IEventController {

    void createEvent(EventDto eventDto);
    EventDto readEvent(EventDto eventDto);
    EventDto updateEventDescription(EventDto eventDto, String newDescription);
    void deleteEvent(EventDto eventDto);

}
