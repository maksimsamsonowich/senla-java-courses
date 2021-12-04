package com.github.service;

import com.github.dto.UserDto;
import com.github.entity.User;
import com.github.mapper.Mapper;
import com.github.repository.UserRepository;
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
public class UserServiceTest {

    private User userMock;

    @Mock
    private UserRepository userDao;

    @InjectMocks
    private UserService userService;

    @Mock
    private Mapper<UserDto, User> userMapper;

    @Before
    public void setup() {
        userMock = new User();
        userMock.setId(1);
        userMock.setFirstName("manoftheyear");
        userMock.setSurname("Max");
        userMock.setFirstName("Max");
    }

    @Test
    public void createUserSuccess() {

        Mockito.when(userDao.create(userMock)).thenReturn(userMock);

        UserDto expectedResult = userMapper.toDto(userMock, UserDto.class);
        UserDto actualResult = userService.createUser(expectedResult);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void readUserSuccess() {

        Mockito.when(userDao.read(userMock.getId())).thenReturn(userMock);

        UserDto expectedResult = userMapper.toDto(userMock, UserDto.class);
        UserDto actualResult = userService.readUser(userMock.getId());

        Assert.assertEquals(expectedResult, actualResult);

    }

    @Test
    public void updateUserSuccess() {

        Mockito.when(userDao.update(userMock)).thenReturn(userMock);

        UserDto expectedResult = new UserDto();
        UserDto actualResult = userService.update(userMock.getId(), expectedResult);

        Assert.assertNull(actualResult);
    }

    @Test
    public void deleteUserSuccess() {

        doNothing().when(userDao).delete(userMock);
        Mockito.when(userDao.read(userMock.getId())).thenReturn(userMock);

        userService.deleteUser(userMock.getId());

        verify(userDao, times(1)).delete(userMock);
    }

}
