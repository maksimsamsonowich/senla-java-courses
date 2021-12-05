package com.github.repository;

import com.github.configs.root.DatabaseConfig;
import com.github.entity.Event;
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
import java.util.HashSet;
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
    private Event testEventEntity;

    @Before
    public void getTestEntity() {
        testEventEntity = new Event();

        testEventEntity.setTitle("Title");
        testEventEntity.setDescription("Desc");
        testEventEntity.setAgeLimit((short) 18);
        testEventEntity.setOccupiedPlace((short) 11);
        testEventEntity.setDate(Date.valueOf("2021-11-29"));
    }

    @Test
    public void createEventSuccess() {
        eventDao.create(testEventEntity);
        testEventEntity.setId(1);

        Event secondEvent = eventDao.read(1);

        Assert.assertEquals(testEventEntity, secondEvent);
    }

    @Test
    public void deleteEventSuccess() {
        eventDao.create(testEventEntity);

        testEventEntity.setId(1);
        eventDao.delete(testEventEntity);

        Event secondEvent = eventDao.read(2);
        Assert.assertNull(secondEvent);
    }

    @Test
    public void readEventSuccess() {
        eventDao.create(testEventEntity);
        testEventEntity.setId(1);

        Event event = eventDao.read(1);

        Assert.assertEquals(event, testEventEntity);
    }

    @Test
    public void updateEventSuccess() {
        eventDao.create(testEventEntity);

        testEventEntity.setId(1);
        testEventEntity.setAgeLimit((short) 20);
        eventDao.update(testEventEntity);

        Event event = eventDao.read(1);

        Assert.assertEquals(event, testEventEntity);
    }

    @Test
    public void getEventByLocationSuccess() {
        Set<Event> events = eventDao.getEventsByLocation(1);
        Event eventMock = eventDao.read(1);

        Set<Event> eventSetMock = new HashSet<>();
        eventSetMock.add(eventMock);

        Assert.assertEquals(events, eventSetMock);
    }

}
