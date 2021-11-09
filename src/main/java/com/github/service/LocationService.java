package com.github.service;

import com.github.dao.IDao;
import com.github.dto.LocationDto;
import com.github.entity.Location;
import com.github.mappers.LocationMapper;
import org.springframework.stereotype.Component;

@Component
public class LocationService implements ILocationService {

    private final LocationMapper locationMapper;
    private final IDao<Location> iLocationDao;

    public LocationService(LocationMapper locationMapper, IDao<Location> iLocationDao) {
        this.locationMapper = locationMapper;
        this.iLocationDao = iLocationDao;
    }

    @Override
    public void createLocation(LocationDto locationDto) {
        iLocationDao.create(locationMapper.toEntity(locationDto));
    }

    @Override
    public LocationDto readLocation(LocationDto locationDto) {
        return locationMapper.toDto(iLocationDao.read(locationMapper.toEntity(locationDto)));
    }

    @Override
    public LocationDto updateLocationAddress(LocationDto locationDto, String newAddress) {
        return locationMapper.toDto(iLocationDao.update(locationMapper.toEntity(locationDto), newAddress));
    }

    @Override
    public void deleteLocation(LocationDto locationDto) {
        iLocationDao.delete(locationMapper.toEntity(locationDto));
    }

}
