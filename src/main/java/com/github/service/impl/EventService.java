package com.github.service.impl;

import com.github.dto.EventDto;
import com.github.entity.Event;
import com.github.mapper.IMapper;
import com.github.repository.impl.EventRepository;
import com.github.service.IEventService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

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

        return eventMapper.toDto(currentEvent, EventDto.class);
    }

    @Override
    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<EventDto> getEventsByLocation(Long eventId) {
        Set<Event> currentEvents = eventRepository.getEventsByLocation(eventId);

        return eventMapper.setToDto(currentEvents, EventDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<EventDto> getLimitedCheapestEvents(Integer resultLimit) {
        Set<Event> currentEvents = eventRepository.getCheapestEvents(resultLimit);

        return eventMapper.setToDto(currentEvents, EventDto.class);
    }

}
