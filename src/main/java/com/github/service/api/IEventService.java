package com.github.service.api;

import com.github.dto.EventDto;
import com.github.dto.LocationDto;

import java.util.Set;

public interface IEventService {

    EventDto createEvent(EventDto eventDto);

    EventDto readEvent(Integer id);

    EventDto update(EventDto eventDto);

    void deleteEvent(EventDto eventDto);

    Set<EventDto> getEventsByLocation(LocationDto locationDto);

}
