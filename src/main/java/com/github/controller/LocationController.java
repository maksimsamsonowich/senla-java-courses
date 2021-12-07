package com.github.controller;

import com.github.dto.LocationDto;
import com.github.service.ILocationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("location-management")
public class LocationController {

    private ILocationService iLocationService;

    @PostMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<LocationDto> createLocation(@RequestBody LocationDto locationDto) {
        return ResponseEntity.ok(iLocationService.createLocation(locationDto));
    }

    @GetMapping("{locationId}")
    public ResponseEntity<LocationDto> readLocation(@PathVariable Integer locationId) {
        return ResponseEntity.ok(iLocationService.readLocation(locationId));
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("{locationId}")
    public ResponseEntity<LocationDto> updateLocation(@PathVariable Integer locationId, @RequestBody LocationDto locationDto) {
        return ResponseEntity.ok(iLocationService.update(locationId, locationDto));
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("{locationId}")
    public void deleteLocation(@PathVariable("locationId") Integer locationId) {
        iLocationService.deleteLocation(locationId);
    }

    @GetMapping("by-event/{eventId}")
    public ResponseEntity<LocationDto> getEventLocation(@PathVariable Integer eventId) {
        return ResponseEntity.ok(iLocationService.getEventLocation(eventId));
    }
}

