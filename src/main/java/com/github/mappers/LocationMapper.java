package com.github.mappers;

import com.github.dto.EventDto;
import com.github.dto.LocationDto;
import com.github.entity.Event;
import com.github.entity.Location;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class LocationMapper {

    private final ModelMapper mapper;

    public LocationMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Location toEntity(LocationDto locationDto) {
        return Objects.isNull(locationDto) ? null : mapper.map(locationDto, Location.class);
    }

    public LocationDto toDto(Location entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, LocationDto.class);
    }
}
