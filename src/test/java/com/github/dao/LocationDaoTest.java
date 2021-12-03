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

@WebAppConfiguration
@RunWith(SpringRunner.class)
@ContextConfiguration(
        classes = { DatabaseConfig.class },
        loader = AnnotationConfigContextLoader.class)
@Transactional
public class LocationDaoTest {

    @Resource
    private LocationDao locationDao;

    @Mock
    private Location testLocationEntity;

    @Before
    public void getTestEntity() {
        testLocationEntity = new Location();
        testLocationEntity.setId(1);
        testLocationEntity.setAddress("Кабяка 11");
        testLocationEntity.setTitle("NN");
        testLocationEntity.setCapacity(12);
    }

    @Test
    public void createLocationSuccess() {
        locationDao.create(testLocationEntity);

        Location secondLocation = locationDao.read(1);

        Assert.assertEquals(testLocationEntity, secondLocation);
    }

    @Test
    public void deleteLocationSuccess() {
        locationDao.create(testLocationEntity);

        locationDao.delete(testLocationEntity);

        Location secondLocation = locationDao.read(1);
        Assert.assertNull(secondLocation);
    }

    @Test
    public void readLocationSuccess() {
        locationDao.create(testLocationEntity);

        Location secondLocation = locationDao.read(1);

        Assert.assertEquals(secondLocation, testLocationEntity);
    }

    @Test
    public void updateLocationSuccess() {
        locationDao.create(testLocationEntity);

        testLocationEntity.setTitle("KK");
        locationDao.update(testLocationEntity);

        Location secondLocation = locationDao.read(1);

        Assert.assertEquals(secondLocation, testLocationEntity);
    }

}
