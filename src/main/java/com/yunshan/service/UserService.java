package com.yunshan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.yunshan.bean.User;
import com.yunshan.mapper.UserMapper;

@Service("userService")
public class UserService {

    @Autowired
    @Qualifier("userMapper")
    private UserMapper userMapper;
    
    public User queryById(int id) throws Exception {
        return userMapper.getUserByID(id);
    }

}
