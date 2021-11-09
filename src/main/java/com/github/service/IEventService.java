package com.github.service;

import com.github.dto.EventDto;
import com.github.entity.Event;

public interface IEventService {

    void createEvent(EventDto eventDto);

    EventDto readEvent(EventDto eventDto);

    EventDto updateEventDescription(EventDto eventDto, String newDescription);

    void deleteEvent(EventDto eventDto);
}
