package com.github.service;

import com.github.dao.IDao;
import com.github.dto.UserDto;
import com.github.entity.User;
import com.github.mappers.UserMapper;
import org.springframework.stereotype.Component;

@Component
public class UserService implements IUserService {

    private final IDao<User> iUserDao;

    private final UserMapper userMapper;

    public UserService(IDao<User> iUserDao, UserMapper userMapper) {
        this.iUserDao = iUserDao;
        this.userMapper = userMapper;
    }

    @Override
    public void createUser(UserDto userDto) {
        iUserDao.create(userMapper.toEntity(userDto));
    }

    @Override
    public UserDto readUser(UserDto userDto) {
        return userMapper.toDto(iUserDao.read(userMapper.toEntity(userDto)));
    }

    @Override
    public UserDto updateUserEmail(UserDto userDto, String email) {
        return userMapper.toDto(iUserDao.update(userMapper.toEntity(userDto), email));
    }


    @Override
    public void deleteUser(UserDto userDto) {
        iUserDao.delete(userMapper.toEntity(userDto));
    }
}
