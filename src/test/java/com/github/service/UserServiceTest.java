package com.github.service;

import com.github.dao.UserDao;
import com.github.dto.UserDto;
import com.github.entity.User;
import com.github.mapper.Mapper;
import com.github.service.UserService;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

public class UserServiceTest {

    @Mock
    private Mapper<UserDto, User> userMapper;

    @Mock
    private UserService userService;

    @Mock
    private UserDao userDao;

}
