package com.github.controller;

import com.github.dto.LocationDto;
import com.github.service.api.ILocationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("location-management")
public class LocationController {

    private ILocationService iLocationService;

    @PostMapping("{locationId}")
    public void createLocation(@PathVariable Integer locationId, @RequestBody LocationDto locationDto) {
        iLocationService.createLocation(locationId, locationDto);
    }

    @GetMapping("{locationId}")
    public LocationDto readLocation(@PathVariable Integer locationId) {
        return iLocationService.readLocation(locationId);
    }

    @PutMapping("{locationId}")
    public LocationDto updateLocation(@PathVariable Integer locationId, @RequestBody LocationDto locationDto) {
        return iLocationService.update(locationId, locationDto);
    }

    @DeleteMapping("{locationId}")
    public void deleteLocation(@PathVariable("locationId") Integer locationId) {
        iLocationService.deleteLocation(locationId);
    }

    @GetMapping("by-event/{eventId}")
    public LocationDto getEventLocation(@PathVariable Integer locationId) {
        return iLocationService.getEventLocation(locationId);
    }
}

