package com.github.service;

import com.github.dto.EventDto;

import java.util.Set;

public interface IEventService {

    EventDto createEvent(EventDto eventDto);

    EventDto readEvent(Long  id);

    EventDto update(Long id, EventDto eventDto);

    void deleteEvent(Long id);

    Set<EventDto> getEventsByLocation(Long id);

    Set<EventDto> getLimitedCheapestEvents(Integer resultLimit);
}