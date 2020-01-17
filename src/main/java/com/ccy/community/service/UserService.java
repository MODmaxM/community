package com.ccy.community.service;


import com.ccy.community.dao.UserMapper;
import com.ccy.community.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public User findById(int id) {
        return userMapper.selectById(id);
    }
}
