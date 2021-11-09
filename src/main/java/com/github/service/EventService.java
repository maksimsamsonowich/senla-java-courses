package com.github.service;

import com.github.dao.IDao;
import com.github.dto.EventDto;

import com.github.entity.Event;

import com.github.mapper.IMapper;
import org.springframework.stereotype.Component;

@Component
public class EventService implements IEventService {

    private final IDao<Event> iEventDao;
    private final IMapper<EventDto, Event> eventMapper;

    public EventService(IDao<Event> iEventDao, IMapper<EventDto, Event> eventMapper) {
        this.iEventDao = iEventDao;
        this.eventMapper = eventMapper;
    }

    @Override
    public void createEvent(EventDto eventDto) {
        iEventDao.create(eventMapper.toEntity(eventDto, Event.class));
    }

    @Override
    public EventDto readEvent(EventDto eventDto) {
        return eventMapper.toDto(iEventDao.read(eventMapper.toEntity(eventDto, Event.class)), EventDto.class);
    }

    @Override
    public EventDto update(EventDto eventDto) {
        return eventMapper.toDto(iEventDao.update(eventMapper.toEntity(eventDto, Event.class)), EventDto.class);
    }

    @Override
    public void deleteEvent(EventDto eventDto) {
        iEventDao.delete(eventMapper.toEntity(eventDto, Event.class));
    }
}
