package com.github.service;

import com.github.dao.IDao;
import com.github.dao.LocationDao;
import com.github.dto.LocationDto;
import com.github.entity.Location;
import com.github.mapper.IMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
@AllArgsConstructor
public class LocationService implements ILocationService {

    private final IMapper<LocationDto, Location> locationMapper;

    private final LocationDao iLocationDao;

    @Override
    public void createLocation(LocationDto locationDto) {
        iLocationDao.create(locationMapper.toEntity(locationDto, Location.class));
    }

    @Override
    public LocationDto readLocation(LocationDto locationDto) {
        return locationMapper.toDto(iLocationDao.read(locationMapper.toEntity(locationDto, Location.class)), LocationDto.class);
    }

    @Override
    public LocationDto update(LocationDto locationDto) {
        return locationMapper.toDto(iLocationDao.update(locationMapper.toEntity(locationDto, Location.class)), LocationDto.class);
    }

    @Override
    public void deleteLocation(LocationDto locationDto) {
        iLocationDao.delete(locationMapper.toEntity(locationDto, Location.class));
    }

}
