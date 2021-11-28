package com.github.service;

import com.github.dao.LocationDao;
import com.github.dto.EventDto;
import com.github.dto.LocationDto;
import com.github.entity.Location;
import com.github.mapper.Mapper;
import com.github.service.LocationService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class LocationServiceTest {

    @Mock
    private Mapper<LocationDto, Location> locationMapper;

    @Mock
    private LocationService locationService;

    @Mock
    private LocationDao locationDao;

    @Test
    public void createEventTest() {
        Location locationEntityMock = new Location();
        Mockito.when(locationDao.create(locationEntityMock)).thenReturn(locationEntityMock);
        LocationDto locationDtoMock = locationMapper.toDto(locationEntityMock, LocationDto.class);

        LocationDto locationDto = locationService.createLocation(locationDtoMock);

        Assert.assertEquals(locationDto, locationDtoMock);
    }

    @Test
    public void readEventTest() {
        final Integer testId = 1;

        Location locationEntityMock = new Location();
        Mockito.when(locationDao.read(testId)).thenReturn(locationEntityMock);
        LocationDto locationDtoMock = locationMapper.toDto(locationEntityMock, EventDto.class);

        LocationDto locationDto = locationService.readLocation(testId);

        Assert.assertEquals(locationDto, locationDtoMock);

    }

    @Test
    public void updateEventTest() {
        Location locationEntityMock = new Location();
        Mockito.when(locationDao.update(locationEntityMock)).thenReturn(locationEntityMock);
        LocationDto locationDtoMock = locationMapper.toDto(locationEntityMock, LocationDto.class);

        LocationDto locationDto = locationService.update(locationDtoMock);

        Assert.assertEquals(locationDto, locationDtoMock);
    }

    @Test
    public void deleteEventTest() {
        LocationDto locationDtoMock = new LocationDto();
        Location locationMock = new Location();

        doNothing().when(locationDao).delete(locationMock);

        locationService.deleteLocation(locationDtoMock);

        verify(locationDao, times(0)).delete(locationMock);
    }

}
