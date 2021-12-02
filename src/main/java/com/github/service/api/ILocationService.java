package com.github.service.api;

import com.github.dto.LocationDto;

public interface ILocationService {

    LocationDto createLocation(LocationDto locationDto);

    LocationDto readLocation(Integer id);

    LocationDto update(Integer id, LocationDto locationDto);

    void deleteLocation(Integer id);

    LocationDto getEventLocation(Integer id);

}
