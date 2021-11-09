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

    public LocationDto updateLocationAddress(LocationDto locationDto, String newAddress) {
        return iLocationService.updateLocationAddress(locationDto, newAddress);
    }

    public void deleteLocation(LocationDto locationDto) {
        iLocationService.deleteLocation(locationDto);
    }
}

