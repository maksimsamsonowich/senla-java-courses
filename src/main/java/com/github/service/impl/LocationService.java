package com.github.service.impl;

import com.github.dto.LocationDto;
import com.github.entity.Location;
import com.github.mapper.IMapper;
import com.github.repository.impl.LocationRepository;
import com.github.service.ILocationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class LocationService implements ILocationService {

    private final LocationRepository locationRepository;

    private final IMapper<LocationDto, Location> locationMapper;

    @Override
    public LocationDto createLocation(LocationDto locationDto) {
        Location currentLocation = locationMapper.toEntity(locationDto, Location.class);

        locationRepository.create(currentLocation);

        return locationMapper.toDto(currentLocation, LocationDto.class);
    }

    @Override
    public LocationDto readLocation(Long locationId) {
        return locationMapper.toDto(locationRepository.readById(locationId), LocationDto.class);
    }

    @Override
    public LocationDto update(Long locationId, LocationDto locationDto) {
        locationDto.setId(locationId);

        Location currentLocation = locationMapper.toEntity(locationDto, Location.class);

        locationRepository.update(currentLocation);

        return locationMapper.toDto(currentLocation, LocationDto.class);
    }

    @Override
    public void deleteLocation(Long locationId) {
        locationRepository.deleteById(locationId);
    }

    @Override
    @Transactional(readOnly = true)
    public LocationDto getEventLocation(Long locationId) {
        return locationMapper.toDto(locationRepository.getLocationByEvent(locationId), LocationDto.class);
    }

}
