package com.github.service;

import com.github.dto.EventDto;
import com.github.filter.PaginationDto;

import java.util.List;
import java.util.Set;

public interface IEventService {

    EventDto createEvent(EventDto eventDto);

    EventDto readEvent(Long  id);

    EventDto update(Long id, EventDto eventDto);

    void deleteEvent(Long id);

    Set<EventDto> getEventsByLocation(Long id);

    List<EventDto> getAllEvents(PaginationDto additionalProperties);

}
