package com.github.service;

import com.github.dao.IDao;
import com.github.dto.EventDto;
import com.github.entity.Event;
import com.github.mappers.EventMapper;

import org.springframework.stereotype.Component;

@Component
public class EventService implements IEventService {

    private final IDao<Event> iEventDao;
    private final EventMapper eventMapper;

    public EventService(IDao<Event> iEventDao, EventMapper eventMapper) {
        this.iEventDao = iEventDao;
        this.eventMapper = eventMapper;
    }

    @Override
    public void createEvent(EventDto eventDto) {
        iEventDao.create(eventMapper.toEntity(eventDto));
    }

    @Override
    public EventDto readEvent(EventDto eventDto) {
        return eventMapper.toDto(iEventDao.read(eventMapper.toEntity(eventDto)));
    }

    @Override
    public EventDto updateEventDescription(EventDto eventDto, String newDescription) {
        return eventMapper.toDto(iEventDao.update(eventMapper.toEntity(eventDto), newDescription));
    }

    @Override
    public void deleteEvent(EventDto eventDto) {
        iEventDao.delete(eventMapper.toEntity(eventDto));
    }
}
