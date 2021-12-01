package com.github.service.api;

import com.github.dto.EventDto;
import com.github.dto.LocationDto;

import java.util.Set;

public interface IEventService {

    EventDto createEvent(Integer id, EventDto eventDto);

    EventDto readEvent(Integer id);

    EventDto update(Integer id, EventDto eventDto);

    void deleteEvent(Integer id);

    Set<EventDto> getEventsByLocation(EventDto eventDto);

}
