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

    @GetMapping
    public String message() {
        return "message";
    }

    @PostMapping("{id}")
    public void createLocation(@PathVariable Integer id, @RequestBody LocationDto locationDto) {
        iLocationService.createLocation(id, locationDto);
    }

    @GetMapping("{id}")
    public LocationDto readLocation(@PathVariable Integer id) {
        return iLocationService.readLocation(id);
    }

    @PutMapping("{id}")
    public LocationDto updateLocation(@PathVariable Integer id, @RequestBody LocationDto locationDto) {
        return iLocationService.update(id, locationDto);
    }

    @DeleteMapping("{id}")
    public void deleteLocation(@PathVariable("id") Integer id) {
        iLocationService.deleteLocation(id);
    }

    @GetMapping("by-event/{id}")
    public LocationDto getEventLocation(@PathVariable Integer id) {
        return iLocationService.getEventLocation(id);
    }
}

