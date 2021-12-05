package com.github.service;

import com.github.dto.EventDto;

import java.util.Set;

public interface IEventService {

    EventDto createEvent(EventDto eventDto);

    EventDto readEvent(Integer  id);

    EventDto update(Integer id, EventDto eventDto);

    void deleteEvent(Integer id);

    Set<EventDto> getEventsByLocation(Integer id);

}
