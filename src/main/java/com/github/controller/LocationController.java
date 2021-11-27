package com.github.controller;

import com.github.dto.LocationDto;
import com.github.service.api.ILocationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/location")
public class LocationController {

    private ILocationService iLocationService;

    @PostMapping("/register/")
    public void createLocation(@RequestBody LocationDto locationDto) {
        iLocationService.createLocation(locationDto);
    }

    @GetMapping("/view/{id}/")
    public LocationDto readLocation(@PathVariable Integer id) {
        return iLocationService.readLocation(id);
    }

    @PutMapping("/edit/{id}/")
    public LocationDto updateLocation(@PathVariable Integer id,
                                      @RequestBody LocationDto locationDto) {
        return iLocationService.update(locationDto);
    }

    @DeleteMapping("/delete/{id}/")
    public void deleteLocation(@PathVariable Integer id,
                               @RequestBody LocationDto locationDto) {
        iLocationService.deleteLocation(locationDto);
    }

    @GetMapping("/event/{id}")
    public LocationDto getEventLocation(@PathVariable Integer id) {
        return iLocationService.getEventLocation(id);
    }
}

