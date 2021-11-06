package com.github.dao;

import com.github.entity.Event;
import com.github.entity.User;
import com.github.exceptions.event.NoSuchEventException;
import com.github.exceptions.user.NoSuchUserException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class EventDao implements IEventDao {

    private Set<Event> events;

    public EventDao() {
        events = new HashSet<>();
    }

    @Override
    public void createEvent(Event event) {
        events.add(event);
    }

    @Override
    public Event readEvent(Event event) {
        return getEntity(event);
    }

    @Override
    public Event updateEventDescription(Event event, String description) {
        Event someEvent = getEntity(event);
        someEvent.setDescription(description);
        return someEvent;
    }

    @Override
    public void deleteEvent(Event event) {
        events.remove(getEntity(event));
    }

    private Event getEntity(Event evnt) {
        return events.stream()
                .filter(event -> event.equals(evnt))
                .findFirst()
                .orElseThrow(() -> new NoSuchEventException("There is no such event;"));
    }

}
