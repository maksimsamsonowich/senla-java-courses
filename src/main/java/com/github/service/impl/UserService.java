package com.github.service.impl;

import com.github.dto.UserDto;
import com.github.entity.User;
import com.github.mapper.IMapper;
import com.github.repository.IAbstractRepository;
import com.github.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class UserService implements IUserService {

    private final IAbstractRepository<User> iUserDao;

    private final IMapper<UserDto, User> userMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        return userMapper.toDto(iUserDao.create(userMapper.toEntity(userDto, User.class)), UserDto.class);
    }

    @Override
    public UserDto readUser(Long id) {
        return userMapper.toDto(iUserDao.read(id), UserDto.class);
    }

    @Override
    public UserDto update(Long id, UserDto userDto) {
        userDto.setId(id);
        return userMapper.toDto(iUserDao.update(userMapper.toEntity(userDto, User.class)), UserDto.class);
    }

    @Override
    public void deleteUser(Long id) {
        iUserDao.delete(iUserDao.read(id));
    }
}
