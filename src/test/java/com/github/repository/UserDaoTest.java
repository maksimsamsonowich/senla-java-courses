package com.github.repository;

import com.github.configs.root.DatabaseConfig;
import com.github.entity.User;
import com.github.repository.impl.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { DatabaseConfig.class })
@Transactional
public class UserDaoTest {

    @Mock
    private User expectedResult;

    @Resource
    private UserRepository userDao;

    @Before
    public void getTestEntity() {
        expectedResult = new User()
                .setFirstName("max")
                .setSurname("max")
                .setPhoneNumber("+375999999999");
    }

    @Test
    public void createUserSuccess() {
        expectedResult = userDao.create(expectedResult);

        User actualResult = userDao.readById(expectedResult.getId());

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void deleteUserSuccess() {
        expectedResult = userDao.create(expectedResult);

        userDao.deleteById(expectedResult.getId());

        User secondUser = userDao.readById(expectedResult.getId());
        Assert.assertNull(secondUser);
    }

    @Test
    public void readUserSuccess() {
        expectedResult = userDao.create(expectedResult);

        User actualResult = userDao.readById(expectedResult.getId());

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void updateUserSuccess() {
        expectedResult = userDao.create(expectedResult);

        expectedResult.setSurname("wow");
        userDao.update(expectedResult);

        User actualResult = userDao.readById(expectedResult.getId());

        Assert.assertEquals(expectedResult, actualResult);
    }
}
