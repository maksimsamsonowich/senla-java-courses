package com.github.service.api;

import com.github.dto.EventDto;
import com.github.dto.LocationDto;

public interface ILocationService {

    void createLocation(LocationDto locationDto);

    LocationDto readLocation(Integer id);

    LocationDto update(LocationDto locationDto);

    void deleteLocation(LocationDto locationDto);

    LocationDto getEventLocation(Integer id);

}
