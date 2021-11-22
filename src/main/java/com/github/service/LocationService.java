package com.github.service;

import com.github.dao.IAbstractDao;
import com.github.dao.LocationDao;
import com.github.dto.LocationDto;
import com.github.entity.Location;
import com.github.mapper.api.IMapper;
import com.github.service.api.ILocationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Component
@AllArgsConstructor
public class LocationService implements ILocationService {

    private final IMapper<LocationDto, Location> locationMapper;

    private final IAbstractDao<Location> iLocationDao;

    @Override
    @Transactional
    public void createLocation(LocationDto locationDto) {
        iLocationDao.create(locationMapper.toEntity(locationDto, Location.class));
    }

    @Override
    @Transactional
    public LocationDto readLocation(LocationDto locationDto) {
        return locationMapper.toDto(iLocationDao.read(locationMapper.toEntity(locationDto, Location.class).getId()), LocationDto.class);
    }

    @Override
    @Transactional
    public LocationDto update(LocationDto locationDto) {
        return locationMapper.toDto(iLocationDao.update(locationMapper.toEntity(locationDto, Location.class)), LocationDto.class);
    }

    @Override
    @Transactional
    public void deleteLocation(LocationDto locationDto) {
        iLocationDao.delete(iLocationDao.read(locationMapper.toEntity(locationDto, Location.class).getId()));
    }

}
