package com.github.service;

import com.github.dao.EventDao;
import com.github.dto.EventDto;
import com.github.dto.LocationDto;
import com.github.entity.Event;
import com.github.entity.Location;
import com.github.mapper.Mapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class EventServiceTest {

    @Mock
    private Mapper<EventDto, Event> eventMapper;

    @Mock
    private EventService eventService;

    @Mock
    private EventDao eventDaoMock;

    @Test
    public void createEventTest() {
        Event eventEntityMock = new Event();
        Mockito.when(eventDaoMock.create(eventEntityMock)).thenReturn(eventEntityMock);
        EventDto eventDtoMock = eventMapper.toDto(eventEntityMock, EventDto.class);

        EventDto event = eventService.createEvent(eventDtoMock.getId(), eventDtoMock);

        Assert.assertEquals(event, eventDtoMock);
    }

    @Test
    public void readEventTest() {
        final int testId = 1;

        Event eventEntityMock = new Event();
        Mockito.when(eventDaoMock.read(testId)).thenReturn(eventEntityMock);
        EventDto eventDtoMock = eventMapper.toDto(eventEntityMock, EventDto.class);

        EventDto event = eventService.readEvent(testId);

        Assert.assertEquals(event, eventDtoMock);

    }

    @Test
    public void updateEventTest() {
        Event eventEntityMock = new Event();
        Mockito.when(eventDaoMock.update(eventEntityMock)).thenReturn(eventEntityMock);
        EventDto eventDtoMock = eventMapper.toDto(eventEntityMock, EventDto.class);

        EventDto event = eventService.update(eventDtoMock.getId(), eventDtoMock);

        Assert.assertEquals(event, eventDtoMock);
    }

    @Test
    public void deleteEventTest() {
        EventDto eventDtoMock = new EventDto();
        Event eventMock = new Event();

        doNothing().when(eventDaoMock).delete(eventMock);

        eventService.deleteEvent(eventDtoMock.getId());

        verify(eventDaoMock, times(0)).delete(eventMock);
    }

    @Test
    public void getEventByLocationTest() {
        final int testId = 1;

        Set<Event> eventEntitySetMock = new HashSet<>();
        Mockito.when(eventDaoMock.getEventsByLocation(testId)).thenReturn(eventEntitySetMock);
        Set<EventDto> eventDtoSetMock = eventMapper.setToDto(eventEntitySetMock, EventDto.class);

        Set<EventDto> event = eventService.getEventsByLocation(testId);

        Assert.assertEquals(event, eventDtoSetMock);
    }
}
