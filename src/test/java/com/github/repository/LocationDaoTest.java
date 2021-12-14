package com.github.repository;

import com.github.configs.root.DatabaseConfig;
import com.github.entity.Location;
import com.github.repository.impl.LocationRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@WebAppConfiguration
@RunWith(SpringRunner.class)
@ContextConfiguration(
        classes = { DatabaseConfig.class },
        loader = AnnotationConfigContextLoader.class)
@Transactional
public class LocationDaoTest {

    @Mock
    private Location expectedResult;

    @Resource
    private LocationRepository locationDao;

    @Before
    public void getTestEntity() {
        expectedResult = new Location()
                .setAddress("Кабяка 11")
                .setTitle("NN")
                .setCapacity(12);
    }

    @Test
    public void createLocationSuccess() {
        expectedResult = locationDao.create(expectedResult);

        Location actualResult = locationDao.read(expectedResult.getId());

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void deleteLocationSuccess() {
        expectedResult = locationDao.create(expectedResult);

        locationDao.delete(expectedResult);

        Location actualResult = locationDao.read(expectedResult.getId());
        Assert.assertNull(actualResult);
    }

    @Test
    public void readLocationSuccess() {
        expectedResult = locationDao.create(expectedResult);

        Location actualResult = locationDao.read(expectedResult.getId());

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void updateLocationSuccess() {
        expectedResult = locationDao.create(expectedResult);

        expectedResult.setTitle("KK");
        locationDao.update(expectedResult);

        Location actualResult = locationDao.read(expectedResult.getId());

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void getAllLocationsSuccess() {
        List<Location> actualResult = locationDao.getAll();

        long step = 1;
        List<Location> expectedResult = new ArrayList<>();

        while (true) {
            Location location = locationDao.read(step);

            if (location == null)
                break;

            expectedResult.add(location);
            step += 1;
        }

        Assert.assertEquals(expectedResult, actualResult);
    }

}
