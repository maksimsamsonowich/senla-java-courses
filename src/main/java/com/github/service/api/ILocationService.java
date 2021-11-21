package com.github.service.api;

import com.github.dto.LocationDto;

public interface ILocationService {

    void createLocation(LocationDto locationDto);

    LocationDto readLocation(LocationDto locationDto);

    LocationDto update(LocationDto locationDto);

    void deleteLocation(LocationDto locationDto);

}
