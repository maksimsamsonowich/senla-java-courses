package com.github.service;

import com.github.dto.LocationDto;

public interface ILocationService {

    LocationDto createLocation(LocationDto locationDto);

    LocationDto readLocation(Long id);

    LocationDto update(Long id, LocationDto locationDto);

    void deleteLocation(Long id);

    LocationDto getEventLocation(Long id);

}
