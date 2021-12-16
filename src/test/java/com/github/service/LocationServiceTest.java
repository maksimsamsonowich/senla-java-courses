package com.github.service;

import com.github.dto.LocationDto;
import com.github.entity.Location;
import com.github.mapper.impl.Mapper;
import com.github.repository.impl.LocationRepository;
import com.github.service.impl.LocationService;
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
    private LocationRepository locationDao;

    @InjectMocks
    private LocationService locationService;

    @Mock
    private Mapper<LocationDto, Location> locationMapper;

    @Before
    public void setup() {
        locationMock = new Location()
                .setTitle("BAR")
                .setCapacity(50)
                .setAddress("FFF");
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

        Mockito.when(locationDao.readById(locationMock.getId())).thenReturn(locationMock);

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

        doNothing().when(locationDao).deleteById(locationMock);
        Mockito.when(locationDao.readById(locationMock.getId())).thenReturn(locationMock);

        locationService.deleteLocation(locationMock.getId());

        verify(locationDao, times(1)).deleteById(locationMock);
    }

    @Test
    public void getEventLocationSuccess() {

        Mockito.when(locationDao.getLocationByEvent(locationMock.getId())).thenReturn(locationMock);

        LocationDto expectedResult = locationMapper.toDto(locationMock, LocationDto.class);
        LocationDto actualResult = locationService.getEventLocation(locationMock.getId());

        Assert.assertEquals(expectedResult, actualResult);
    }
}
