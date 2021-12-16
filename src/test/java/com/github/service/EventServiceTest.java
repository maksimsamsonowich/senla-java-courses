package com.github.service;

import com.github.dto.EventDto;
import com.github.entity.Event;
import com.github.mapper.impl.Mapper;
import com.github.repository.impl.EventRepository;
import com.github.service.impl.EventService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class EventServiceTest {
    private Event eventMock;

    @Mock
    private EventRepository eventDaoMock;

    @InjectMocks
    private EventService eventService;

    @Mock
    private Mapper<EventDto, Event> eventMapper;

    @Before
    public void setup() {
        eventMock = new Event()
                .setTitle("Wow")
                .setAgeLimit((short) 18)
                .setOccupiedPlace((short) 199)
                .setDescription("Trust nobody");
    }

    @Test
    public void createEventSuccess() {

        Mockito.when(eventDaoMock.create(eventMock)).thenReturn(eventMock);

        EventDto expectedResult = eventMapper.toDto(eventMock, EventDto.class);
        EventDto actualResult = eventService.createEvent(expectedResult);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void readEventSuccess() {

        Mockito.when(eventDaoMock.readById(eventMock.getId())).thenReturn(eventMock);

        EventDto expectedResult = eventMapper.toDto(eventMock, EventDto.class);
        EventDto actualResult = eventService.readEvent(eventMock.getId());

        Assert.assertEquals(expectedResult, actualResult);

    }

    @Test
    public void updateEventSuccess() {

        Mockito.when(eventDaoMock.update(eventMock)).thenReturn(eventMock);

        EventDto expectedResult = new EventDto();
        EventDto actualResult = eventService.update(expectedResult.getId(), expectedResult);

        Assert.assertNull(actualResult);
    }

    @Test
    public void deleteEventSuccess() {

        doNothing().when(eventDaoMock).deleteById(eventMock);
        Mockito.when(eventDaoMock.readById(eventMock.getId())).thenReturn(eventMock);

        eventService.deleteEvent(eventMock.getId());

        verify(eventDaoMock, times(1)).deleteById(eventMock);

    }

    @Test
    public void getEventByLocationSuccess() {

        Set<Event> eventEntitySetMock = new HashSet<>();

        Mockito.when(eventDaoMock.getEventsByLocation(eventMock.getId())).thenReturn(eventEntitySetMock);

        Set<EventDto> expectedResult = eventMapper.setToDto(eventEntitySetMock, EventDto.class);
        Set<EventDto> actualResult = eventService.getEventsByLocation(eventMock.getId());

        Assert.assertEquals(expectedResult, actualResult);
    }
}
