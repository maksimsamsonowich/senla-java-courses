package com.github.service;

import com.github.dao.IUserDao;
import org.springframework.stereotype.Component;


@Component
public class Service implements IService {

    private final IUserDao iUserDao;

    public Service(IUserDao iUserDao) {
        this.iUserDao = iUserDao;
    }

    @Override
    public String getSomething(){
        return iUserDao.getSomething();
    }
}