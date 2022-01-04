package com.github.service.impl;

import com.github.dto.EventDto;
import com.github.entity.Event;
import com.github.filter.PaginationDto;
import com.github.mapper.IMapper;
import com.github.repository.impl.EventRepository;
import com.github.service.IEventService;
import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class EventService implements IEventService {

    private final EventRepository eventRepository;

    private final IMapper<EventDto, Event> eventMapper;

    @Override
    public EventDto createEvent(EventDto eventDto) {
        Event currentEvent = eventMapper.toEntity(eventDto, Event.class);

        eventRepository.create(currentEvent);

        log.info("Event " + currentEvent.getId() + " successfully created.");

        return eventMapper.toDto(currentEvent, EventDto.class);
    }

    @Override
    public EventDto readEvent(Long eventId) {
        return eventMapper.toDto(eventRepository.readById(eventId), EventDto.class);
    }

    @Override
    public EventDto update(Long eventId, EventDto eventDto) {
        eventDto.setId(eventId);
        Event currentEvent = eventMapper.toEntity(eventDto, Event.class);

        eventRepository.update(currentEvent);

        log.info("Event " + eventId + " successfully updated.");

        return eventMapper.toDto(currentEvent, EventDto.class);
    }

    @Override
    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);

        log.info("Event " + eventId + " successfully deleted.");
    }

    @Override
    @Transactional(readOnly = true)
    public Set<EventDto> getEventsByLocation(Long eventId) {
        Set<Event> currentEvents = eventRepository.getEventsByLocation(eventId);

        log.info("Events successfully pulled.");

        return eventMapper.setToDto(currentEvents, EventDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<EventDto> getAllEvents(PaginationDto additionalProperties) {
        Set<Event> currentEvents = Sets.newHashSet(eventRepository.getAll(additionalProperties));

        log.info("Events successfully pulled.");

        return eventMapper.setToDto(currentEvents, EventDto.class);
    }
}
