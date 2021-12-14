package com.github.repository;

import com.github.configs.root.DatabaseConfig;
import com.github.entity.Ticket;
import com.github.entity.User;
import com.github.repository.impl.TicketRepository;
import com.github.repository.impl.UserRepository;
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

        User actualResult = userDao.read(expectedResult.getId());

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void deleteUserSuccess() {
        expectedResult = userDao.create(expectedResult);

        userDao.delete(expectedResult);

        User secondUser = userDao.read(expectedResult.getId());
        Assert.assertNull(secondUser);
    }

    @Test
    public void readUserSuccess() {
        expectedResult = userDao.create(expectedResult);

        User actualResult = userDao.read(expectedResult.getId());

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void updateUserSuccess() {
        expectedResult = userDao.create(expectedResult);

        expectedResult.setSurname("wow");
        userDao.update(expectedResult);

        User actualResult = userDao.read(expectedResult.getId());

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getAllTicketsSuccess() {
        List<User> actualResult = userDao.getAll();

        long step = 1;
        List<User> expectedResult = new ArrayList<>();

        while (true) {
            User user = userDao.read(step);

            if (user == null) {
                break;
            }

            expectedResult.add(user);
            step += 1;
        }

        Assert.assertEquals(expectedResult, actualResult);
    }
}
