package com.github.service;

import com.github.dao.IAbstractDao;
import com.github.dto.EventDto;

import com.github.entity.Event;

import com.github.mapper.api.IMapper;
import com.github.service.api.IEventService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
@AllArgsConstructor
public class EventService implements IEventService {

    private final IAbstractDao<Event> iEventDao;

    private final IMapper<EventDto, Event> eventMapper;

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
