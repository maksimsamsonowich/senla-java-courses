package com.github.dao;

import com.github.configs.root.DatabaseConfig;
import com.github.entity.User;
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
public class UserDaoTest {

    @Resource
    private UserDao userDao;

    @Mock
    private User testUserEntity;

    @Before
    public void getTestEntity() {
        testUserEntity = new User();
        testUserEntity.setId(1);
        testUserEntity.setLogin("max");
        testUserEntity.setPassword("max");
        testUserEntity.setEmail("max");
        testUserEntity.setFirstName("max");
        testUserEntity.setSurname("max");
        testUserEntity.setPhoneNumber("+375999999999");
    }

    @Test
    public void createUserSuccess() {
        userDao.create(testUserEntity);

        User secondUser = userDao.read(1);

        Assert.assertEquals(testUserEntity, secondUser);
    }

    @Test
    public void deleteUserSuccess() {
        userDao.create(testUserEntity);

        userDao.delete(testUserEntity);

        User secondUser = userDao.read(1);
        Assert.assertNull(secondUser);
    }

    @Test
    public void readUserSuccess() {
        userDao.create(testUserEntity);

        User secondUser = userDao.read(1);

        Assert.assertEquals(secondUser, testUserEntity);
    }

    @Test
    public void updateUserSuccess() {
        userDao.create(testUserEntity);

        testUserEntity.setSurname("wow");
        userDao.update(testUserEntity);

        User secondUser = userDao.read(1);

        Assert.assertEquals(secondUser, testUserEntity);
    }

}
