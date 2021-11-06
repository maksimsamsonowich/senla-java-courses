package com.github.service;

import com.github.dao.IEventDao;
import com.github.dto.EventDto;
import com.github.entity.Event;
import com.github.mappers.EventMapper;

import org.springframework.stereotype.Component;

@Component
public class EventService implements IEventService {

    private final IEventDao iEventDao;
    private final EventMapper eventMapper;

    public EventService(IEventDao iEventDao, EventMapper eventMapper) {
        this.iEventDao = iEventDao;
        this.eventMapper = eventMapper;
    }

    @Override
    public void createEvent(EventDto eventDto) {
        iEventDao.createEvent(eventMapper.toEntity(eventDto));
    }

    @Override
    public EventDto readEvent(EventDto eventDto) {
        return eventMapper.toDto(iEventDao.readEvent(eventMapper.toEntity(eventDto)));
    }

    @Override
    public EventDto updateEventDescription(EventDto eventDto, String newDescription) {
        return eventMapper.toDto(iEventDao.updateEventDescription(eventMapper.toEntity(eventDto), newDescription));
    }

    @Override
    public void deleteEvent(EventDto eventDto) {
        iEventDao.deleteEvent(eventMapper.toEntity(eventDto));
    }
}
