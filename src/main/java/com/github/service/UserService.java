package com.github.service;

import com.github.dao.IUserDao;
import com.github.dto.UserDto;
import com.github.entity.User;
import com.github.mappers.UserMapper;
import org.springframework.stereotype.Component;

@Component
public class UserService implements IUserService {

    private IUserDao iUserDao;

    private final UserMapper userMapper;

    public UserService(IUserDao iUserDao, UserMapper userMapper) {
        this.iUserDao = iUserDao;
        this.userMapper = userMapper;
    }

    @Override
    public void createUser(UserDto userDto) {
        iUserDao.createUser(userMapper.toEntity(userDto));
    }

    @Override
    public UserDto readUser(UserDto userDto) {
        return userMapper.toDto(iUserDao.readUser(userMapper.toEntity(userDto)));
    }

    @Override
    public UserDto updateUserEmail(UserDto userDto, String email) {
        return userMapper.toDto(iUserDao.updateUserEmail(userMapper.toEntity(userDto), email));
    }

    @Override
    public void deleteUser(UserDto userDto) {
        iUserDao.deleteUser(userMapper.toEntity(userDto));
    }
}
