package com.github.service;

import com.github.dao.IAbstractDao;
import com.github.dto.UserDto;
import com.github.entity.User;
import com.github.mapper.api.IMapper;
import com.github.service.api.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private final IAbstractDao<User> iUserDao;

    private final IMapper<UserDto, User> userMapper;

    @Override
    public void createUser(UserDto userDto) {
        iUserDao.create(userMapper.toEntity(userDto, User.class));
    }

    @Override
    public UserDto readUser(UserDto userDto) {
        return userMapper.toDto(iUserDao.read(userMapper.toEntity(userDto, User.class)), UserDto.class);
    }

    @Override
    public UserDto update(UserDto userDto) {
        return userMapper.toDto(iUserDao.update(userMapper.toEntity(userDto, User.class)), UserDto.class);
    }

    @Override
    public void deleteUser(UserDto userDto) {
        iUserDao.delete(userMapper.toEntity(userDto, User.class));
    }
}
