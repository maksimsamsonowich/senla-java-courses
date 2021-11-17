package com.github.controller;


import com.github.dto.LocationDto;
import com.github.service.ILocationService;
import org.springframework.stereotype.Component;

@Component
public class LocationController {

    private ILocationService iLocationService;

    public LocationController(ILocationService iLocationService) {
        this.iLocationService = iLocationService;
    }

    public void createLocation(LocationDto locationDto) {
        iLocationService.createLocation(locationDto);
    }

    public LocationDto readLocation(LocationDto locationDto) {
        return iLocationService.readLocation(locationDto);
    }

    public LocationDto updateLocation(LocationDto locationDto) {
        return iLocationService.update(locationDto);
    }

    public void deleteLocation(LocationDto locationDto) {
        iLocationService.deleteLocation(locationDto);
    }
}

