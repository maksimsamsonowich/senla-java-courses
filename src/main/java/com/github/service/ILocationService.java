package com.github.service;

import com.github.dto.LocationDto;

public interface ILocationService {

    void createLocation(LocationDto locationDto);

    LocationDto readLocation(LocationDto locationDto);

    LocationDto updateLocationAddress(LocationDto locationDto, String newAddress);

    void deleteLocation(LocationDto locationDto);

}
