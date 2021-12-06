package com.github.dao;

import com.github.configs.root.DatabaseConfig;
import com.github.entity.Location;
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

@Transactional
@WebAppConfiguration
@RunWith(SpringRunner.class)
@ContextConfiguration(
        classes = { DatabaseConfig.class },
        loader = AnnotationConfigContextLoader.class)
public class LocationDaoTest {

    @Resource
    private LocationDao locationDao;

    @Mock
    private Location expectedResult;

    @Before
    public void getTestEntity() {
        expectedResult = new Location();
        expectedResult.setAddress("Кабяка 11");
        expectedResult.setTitle("NN");
        expectedResult.setCapacity(12);
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

}
