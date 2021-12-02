package com.github.service;

import com.github.dao.UserDao;
import com.github.dto.UserDto;
import com.github.entity.User;
import com.github.mapper.Mapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserServiceTest {

    @Mock
    private Mapper<UserDto, User> userMapper;

    @Mock
    private UserService userService;

    @Mock
    private UserDao userDao;

    @Test
    public void createUserTest() {
        User userEntityMock = new User();
        Mockito.when(userDao.create(userEntityMock)).thenReturn(userEntityMock);
        UserDto userDtoMock = userMapper.toDto(userEntityMock, UserDto.class);

        UserDto userDto = userService.createUser(userDtoMock);

        Assert.assertEquals(userDto, userDtoMock);
    }

    @Test
    public void readUserTest() {
        final int testId = 1;

        User userEntityMock = new User();
        Mockito.when(userDao.read(testId)).thenReturn(userEntityMock);
        UserDto userDtoMock = userMapper.toDto(userEntityMock, UserDto.class);

        UserDto userDto = userService.readUser(testId);

        Assert.assertEquals(userDto, userDtoMock);

    }

    @Test
    public void updateUserTest() {
        User userEntityMock = new User();
        Mockito.when(userDao.update(userEntityMock)).thenReturn(userEntityMock);
        UserDto userDtoMock = userMapper.toDto(userEntityMock, UserDto.class);

        UserDto userDto = userService.update(userDtoMock.getId(), userDtoMock);

        Assert.assertEquals(userDto, userDtoMock);
    }

    @Test
    public void deleteUserTest() {
        UserDto userDtoMock = new UserDto();
        User userEntityMock = new User();

        doNothing().when(userDao).delete(userEntityMock);

        userService.deleteUser(userDtoMock.getId());

        verify(userDao, times(0)).delete(userEntityMock);
    }

}
