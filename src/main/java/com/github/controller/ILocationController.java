package com.github.controller;

import com.github.dto.LocationDto;

public interface ILocationController {

    void createLocation(LocationDto locationDto);
    LocationDto readLocation(LocationDto locationDto);
    LocationDto updateLocationAddress(LocationDto locationDto, String newAddress);
    void deleteLocation(LocationDto locationDto);

}
