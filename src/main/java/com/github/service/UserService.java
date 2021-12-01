package com.github.service;

import com.github.dao.api.IAbstractDao;
import com.github.dto.UserDto;
import com.github.entity.User;
import com.github.mapper.api.IMapper;
import com.github.service.api.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Component
@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private final IAbstractDao<User> iUserDao;

    private final IMapper<UserDto, User> userMapper;

    @Override
    @Transactional
    public UserDto createUser(Integer id, UserDto userDto) {
        userDto.setId(id);
        return userMapper.toDto(iUserDao.create(userMapper.toEntity(userDto, User.class)), UserDto.class);
    }

    @Override
    @Transactional
    public UserDto readUser(Integer id) {
        return userMapper.toDto(iUserDao.read(id), UserDto.class);
    }

    @Override
    @Transactional
    public UserDto update(Integer id, UserDto userDto) {
        userDto.setId(id);
        return userMapper.toDto(iUserDao.update(userMapper.toEntity(userDto, User.class)), UserDto.class);
    }

    @Override
    @Transactional
    public void deleteUser(Integer id) {
        iUserDao.delete(iUserDao.read(id));
    }
}
