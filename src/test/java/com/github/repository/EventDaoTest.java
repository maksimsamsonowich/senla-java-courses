package com.github.repository;

import com.github.configs.root.DatabaseConfig;
import com.github.entity.Event;
import com.github.entity.Location;
import com.github.repository.impl.EventRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebAppConfiguration
@RunWith(SpringRunner.class)
@ContextConfiguration(
        classes = { DatabaseConfig.class },
        loader = AnnotationConfigContextLoader.class)
@Transactional
public class EventDaoTest {

    @Resource
    private EventRepository eventDao;

    @Mock
    private Event expectedResult;

    @Mock
    private Location locationEntity;

    @Before
    public void getTestEntity() {
        expectedResult = new Event()
                .setTitle("Title")
                .setDescription("Desc")
                .setAgeLimit((short) 18)
                .setOccupiedPlace((short) 11)
                .setDate(Date.valueOf("2021-11-29"));

        locationEntity = new Location()
                .setId(1L);
    }

    @Test
    public void createEventSuccess() {
        expectedResult = eventDao.create(expectedResult);

        Event actualResult = eventDao.read(expectedResult.getId());

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void deleteEventSuccess() {
        expectedResult = eventDao.create(expectedResult);

        eventDao.delete(expectedResult);

        Event actualResult = eventDao.read(expectedResult.getId());
        Assert.assertNull(actualResult);
    }

    @Test
    public void readEventSuccess() {
        expectedResult = eventDao.create(expectedResult);

        Event actualResult = eventDao.read(expectedResult.getId());

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void updateEventSuccess() {
        expectedResult = eventDao.create(expectedResult);

        expectedResult.setAgeLimit((short) 20);
        eventDao.update(expectedResult);

        Event actualResult = eventDao.read(expectedResult.getId());

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getEventByLocationSuccess() {
        Set<Event> events = eventDao.getEventsByLocation(locationEntity.getId());
        Event eventMock = eventDao.read(1L);

        Set<Event> eventSetMock = new HashSet<>();
        eventSetMock.add(eventMock);

        Assert.assertEquals(events, eventSetMock);
    }

    @Test
    public void getAllEventsSuccess() {
        List<Event> actualResult = eventDao.getAll();

        long step = 1;
        List<Event> expectedResult = new ArrayList<Event>();

        while (true) {
            Event event = eventDao.read(step);

            if (event == null) {
                break;
            }

            expectedResult.add(event);
            step += 1;
        }

        Assert.assertEquals(expectedResult, actualResult);
    }

}
