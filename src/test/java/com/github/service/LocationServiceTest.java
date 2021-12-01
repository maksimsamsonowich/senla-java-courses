package com.github.service;

import com.github.dao.LocationDao;
import com.github.dto.EventDto;
import com.github.dto.LocationDto;
import com.github.entity.Location;
import com.github.mapper.Mapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

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
    public void createLocationTest() {
        Location locationEntityMock = new Location();
        Mockito.when(locationDao.create(locationEntityMock)).thenReturn(locationEntityMock);
        LocationDto locationDtoMock = locationMapper.toDto(locationEntityMock, LocationDto.class);

        LocationDto locationDto = locationService.createLocation(locationDtoMock.getId(), locationDtoMock);

        Assert.assertEquals(locationDto, locationDtoMock);
    }

    @Test
    public void readLocationTest() {
        final int testId = 1;

        Location locationEntityMock = new Location();
        Mockito.when(locationDao.read(testId)).thenReturn(locationEntityMock);
        LocationDto locationDtoMock = locationMapper.toDto(locationEntityMock, EventDto.class);

        LocationDto locationDto = locationService.readLocation(testId);

        Assert.assertEquals(locationDto, locationDtoMock);

    }

    @Test
    public void updateLocationTest() {
        Location locationEntityMock = new Location();
        Mockito.when(locationDao.update(locationEntityMock)).thenReturn(locationEntityMock);
        LocationDto locationDtoMock = locationMapper.toDto(locationEntityMock, LocationDto.class);

        LocationDto locationDto = locationService.update(locationDtoMock.getId(), locationDtoMock);

        Assert.assertEquals(locationDto, locationDtoMock);
    }

    @Test
    public void deleteLocationTest() {
        LocationDto locationDtoMock = new LocationDto();
        Location locationMock = new Location();

        doNothing().when(locationDao).delete(locationMock);

        locationService.deleteLocation(locationDtoMock.getId());

        verify(locationDao, times(0)).delete(locationMock);
    }

    @Test
    public void getEventLocationTest() {
        final int testId = 1;

        Location locationEntityMock = new Location();
        Mockito.when(locationDao.getLocationByEvent(testId)).thenReturn(locationEntityMock);
        LocationDto locationDtoMock = locationMapper.toDto(locationEntityMock, LocationDto.class);

        LocationDto locationDto = new LocationDto();
        locationDto.setId(testId);

        LocationDto locationDtoSetMock = locationService.getEventLocation(locationDto);

        Assert.assertEquals(locationDtoSetMock, locationDtoMock);
    }

}
