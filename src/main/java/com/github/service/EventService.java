package com.github.service;

import com.github.dao.EventDao;
import com.github.dto.EventArtistDto;
import com.github.dto.EventDto;
import com.github.dto.EventProgramDto;
import com.github.entity.Artist;
import com.github.entity.Event;
import com.github.entity.EventProgram;
import com.github.exceptions.entities.NoSuchEntityException;
import com.github.mapper.api.IMapper;
import com.github.service.api.IEventService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
@AllArgsConstructor
public class EventService implements IEventService {

    private final EventDao iEventDao;

    private final IMapper<EventDto, Event> eventMapper;
    private final IMapper<EventArtistDto, Artist> artistMapper;
    private final IMapper<EventProgramDto, EventProgram> eventProgramMapper;

    @Override
    public EventDto createEvent(EventDto eventDto) {
        return eventMapper.toDto(iEventDao.create(eventMapper.toEntity(eventDto, Event.class)), EventDto.class);
    }

    @Override
    public EventDto readEvent(Integer id) {
        return eventMapper.toDto(iEventDao.read(id), EventDto.class);
    }

    @Override
    public EventDto update(Integer id, EventDto eventDto) {
        eventDto.setId(id);
        return eventMapper.toDto(iEventDao.update(eventMapper.toEntity(eventDto, Event.class)), EventDto.class);
    }

    @Override
    public void deleteEvent(Integer id) {
        iEventDao.delete(iEventDao.read(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Set<EventDto> getEventsByLocation(Integer id) {
        return convertSetOfEntitiesToDto(iEventDao.getEventsByLocation(id));
    }

    private Set<EventDto> convertSetOfEntitiesToDto(Set<Event> events) {
        Set<EventDto> retSet = eventMapper.setToDto(events, EventDto.class);

        for (EventDto eventDto : retSet) {
            eventDto.setEventOrganizer(
                    artistMapper.toDto(
                            events.stream()
                            .filter((entity) -> entity.getId() == eventDto.getId())
                            .findFirst()
                            .orElseThrow(() -> new NoSuchEntityException("There is no such entity"))
                            .getEventOrganizer(),
                             EventArtistDto.class)
            );
            eventDto.setEventProgram(
                    eventProgramMapper.toDto(
                            events.stream()
                                    .filter((entity) -> entity.getId() == eventDto.getId())
                                    .findFirst()
                                    .orElseThrow(() -> new NoSuchEntityException("There is no such entity"))
                                    .getEventProgram(),
                                    EventProgramDto.class)
            );
        }

        return retSet;
    }
}
