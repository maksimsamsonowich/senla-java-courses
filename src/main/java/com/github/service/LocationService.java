package com.github.service;

import com.github.dao.LocationDao;
import com.github.dto.EventDto;
import com.github.dto.LocationDto;
import com.github.entity.Event;
import com.github.entity.Location;
import com.github.mapper.api.IMapper;
import com.github.service.api.ILocationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Component
@Transactional
@AllArgsConstructor
public class LocationService implements ILocationService {

    private final IMapper<LocationDto, Location> locationMapper;
    private final IMapper<EventDto, Event> eventMapper;

    private final LocationDao iLocationDao;

    @Override
    public void createLocation(LocationDto locationDto) {
        iLocationDao.create(locationMapper.toEntity(locationDto, Location.class));
    }

    @Override
    public LocationDto readLocation(LocationDto locationDto) {
        return locationMapper.toDto(iLocationDao.read(locationMapper.toEntity(locationDto, Location.class).getId()), LocationDto.class);
    }

    @Override
    public LocationDto update(LocationDto locationDto) {
        return locationMapper.toDto(iLocationDao.update(locationMapper.toEntity(locationDto, Location.class)), LocationDto.class);
    }

    @Override
    public void deleteLocation(LocationDto locationDto) {
        iLocationDao.delete(iLocationDao.read(locationMapper.toEntity(locationDto, Location.class).getId()));
    }

    @Override
    @Transactional(readOnly = true)
    public LocationDto getEventLocation(EventDto eventDto) {
        return locationMapper.toDto(iLocationDao.getLocationByEvent(eventMapper.toEntity(eventDto, Event.class)), LocationDto.class);
    }

}
