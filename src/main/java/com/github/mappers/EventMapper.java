package com.github.mappers;

import com.github.dto.EventDto;
import com.github.entity.Event;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class EventMapper {

    private final ModelMapper mapper;

    public EventMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Event toEntity(EventDto eventDto) {
        return Objects.isNull(eventDto) ? null : mapper.map(eventDto, Event.class);
    }

    public EventDto toDto(Event entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, EventDto.class);
    }

}
