package com.github.dao;

import com.github.entity.Event;

import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class EventDao extends Dao<Event> {

    public EventDao(Set<Event> events) {
        super(events);
    }

    @Override
    public Event update(Event event, String description) {
        Event someEvent = super.update(event);
        someEvent.setDescription(description);
        return someEvent;
    }
}
