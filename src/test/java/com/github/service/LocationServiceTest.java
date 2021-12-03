package com.github.service;

import com.github.dao.LocationDao;
import com.github.dto.LocationDto;
import com.github.entity.Location;
import com.github.mapper.Mapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class LocationServiceTest {

    private Location locationMock;

    @Mock
    private LocationDao locationDao;

    @InjectMocks
    private LocationService locationService;

    @Mock
    private Mapper<LocationDto, Location> locationMapper;

    @Before
    public void setup() {
        locationMock = new Location();
        locationMock.setId(1);
        locationMock.setTitle("BAR");
        locationMock.setCapacity(50);
        locationMock.setAddress("FFF");
    }

    @Test
    public void createLocationSuccess() {

        Mockito.when(locationDao.create(locationMock)).thenReturn(locationMock);

        LocationDto expectedResult = locationMapper.toDto(locationMock, LocationDto.class);
        LocationDto actualResult = locationService.createLocation(expectedResult);

        Assert.assertEquals(expectedResult, actualResult);

    }

    @Test
    public void readLocationSuccess() {

        Mockito.when(locationDao.read(locationMock.getId())).thenReturn(locationMock);

        LocationDto expectedResult = locationMapper.toDto(locationMock, LocationDto.class);
        LocationDto actualResult = locationService.readLocation(locationMock.getId());

        Assert.assertEquals(expectedResult, actualResult);

    }

    @Test
    public void updateLocationSuccess() {

        Mockito.when(locationDao.update(locationMock)).thenReturn(locationMock);

        LocationDto expectedResult = new LocationDto();
        LocationDto actualResult = locationService.update(locationMock.getId(), expectedResult);

        Assert.assertNull(actualResult);

    }

    @Test
    public void deleteLocationSuccess() {

        doNothing().when(locationDao).delete(locationMock);
        Mockito.when(locationDao.read(locationMock.getId())).thenReturn(locationMock);

        locationService.deleteLocation(locationMock.getId());

        verify(locationDao, times(1)).delete(locationMock);
    }

    @Test
    public void getEventLocationSuccess() {

        Mockito.when(locationDao.getLocationByEvent(locationMock.getId())).thenReturn(locationMock);

        LocationDto expectedResult = locationMapper.toDto(locationMock, LocationDto.class);
        LocationDto actualResult = locationService.getEventLocation(locationMock.getId());

        Assert.assertEquals(expectedResult, actualResult);
    }

}
