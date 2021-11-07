package com.github.service;

import com.github.dao.ILocationDao;
import com.github.dto.LocationDto;
import com.github.mappers.LocationMapper;
import org.springframework.stereotype.Component;

@Component
public class LocationService implements ILocationService {

    private LocationMapper locationMapper;

    private ILocationDao iLocationDao;

    public LocationService(LocationMapper locationMapper, ILocationDao iLocationDao) {
        this.locationMapper = locationMapper;
        this.iLocationDao = iLocationDao;
    }

    @Override
    public void createLocation(LocationDto locationDto) {
        iLocationDao.createLocation(locationMapper.toEntity(locationDto));
    }

    @Override
    public LocationDto readLocation(LocationDto locationDto) {
        return locationMapper.toDto(iLocationDao.readLocation(locationMapper.toEntity(locationDto)));
    }

    @Override
    public LocationDto updateLocationAddress(LocationDto locationDto, String newAddress) {
        return locationMapper.toDto(iLocationDao.updateLocationAddress(locationMapper.toEntity(locationDto), newAddress));
    }

    @Override
    public void deleteLocation(LocationDto locationDto) {
        iLocationDao.deleteLocation(locationMapper.toEntity(locationDto));
    }

}
