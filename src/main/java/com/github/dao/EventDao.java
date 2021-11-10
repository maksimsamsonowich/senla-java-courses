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
    public Event update(Event event) {
        Event someEvent = super.update(event);
        someEvent.setDescription(event.getDescription());
        someEvent.setTitle(event.getTitle());
        someEvent.setAgeLimit(event.getAgeLimit());
        someEvent.setEventOrganizer(event.getEventOrganizer());
        someEvent.setOccupiedPlace(event.getOccupiedPlace());
        return someEvent;
    }
}
