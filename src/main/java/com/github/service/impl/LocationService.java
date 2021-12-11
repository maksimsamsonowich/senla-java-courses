package com.github.service.impl;

import com.github.dto.LocationDto;
import com.github.entity.Location;
import com.github.mapper.IMapper;
import com.github.repository.impl.LocationRepository;
import com.github.service.ILocationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class LocationService implements ILocationService {

    private final IMapper<LocationDto, Location> locationMapper;

    private final LocationRepository iLocationDao;

    @Override
    public LocationDto createLocation(LocationDto locationDto) {
        return locationMapper.toDto(iLocationDao.create(locationMapper.toEntity(locationDto, Location.class)), LocationDto.class);
    }

    @Override
    public LocationDto readLocation(Long id) {
        return locationMapper.toDto(iLocationDao.read(id), LocationDto.class);
    }

    @Override
    public LocationDto update(Long id, LocationDto locationDto) {
        locationDto.setId(id);
        return locationMapper.toDto(iLocationDao.update(locationMapper.toEntity(locationDto, Location.class)), LocationDto.class);
    }

    @Override
    public void deleteLocation(Long id) {
        iLocationDao.delete(iLocationDao.read(id));
    }

    @Override
    @Transactional(readOnly = true)
    public LocationDto getEventLocation(Long id) {
        return locationMapper.toDto(iLocationDao.getLocationByEvent(id), LocationDto.class);
    }

}
