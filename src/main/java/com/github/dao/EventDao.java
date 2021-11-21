package com.github.dao;

import com.github.entity.Event;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@AllArgsConstructor
public class EventDao implements IAbstractDao<Event> {

    @Override
    public void create(Event object) {

    }

    @Override
    public Event read(Event object) {
        return null;
    }

    @Override
    public Event update(Event object) {
        return null;
    }

    @Override
    public void delete(Event object) {

    }
}
