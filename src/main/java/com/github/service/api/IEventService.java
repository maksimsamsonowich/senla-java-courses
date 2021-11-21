package com.github.service.api;

import com.github.dto.EventDto;
import com.github.entity.Event;

public interface IEventService {

    void createEvent(EventDto eventDto);

    EventDto readEvent(EventDto eventDto);

    EventDto update(EventDto eventDto);

    void deleteEvent(EventDto eventDto);
}
