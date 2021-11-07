package com.github.controller;


import com.github.dto.LocationDto;
import com.github.service.ILocationService;
import org.springframework.stereotype.Component;

@Component
public class LocationController implements ILocationController {

    private ILocationService iLocationService;

    public LocationController(ILocationService iLocationService) {
        this.iLocationService = iLocationService;
    }

    @Override
    public void createLocation(LocationDto locationDto) {
        iLocationService.createLocation(locationDto);
    }

    @Override
    public LocationDto readLocation(LocationDto locationDto) {
        return iLocationService.readLocation(locationDto);
    }

    @Override
    public LocationDto updateLocationAddress(LocationDto locationDto, String newAddress) {
        return iLocationService.updateLocationAddress(locationDto, newAddress);
    }

    @Override
    public void deleteLocation(LocationDto locationDto) {
        iLocationService.deleteLocation(locationDto);
    }
}

