package com.github.dao;

import com.github.entity.Event;

public interface IEventDao {

    void createEvent(Event event);
    Event readEvent(Event event);
    Event updateEventDescription(Event event, String description);
    void deleteEvent(Event event);

}
