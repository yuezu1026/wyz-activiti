package com.wyz.activiti7.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyz.activiti7.mapper.UserMapper;
import com.wyz.activiti7.model.User;
import com.wyz.activiti7.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 删除 * * @param id
     */
    @Override
    public void delete(int id) {
        userMapper.deleteByPrimaryKey(id);
    }

    /**
     * 增加 * * @param user
     */
    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }

    /**
     * 更新 * * @param user
     */
    @Override
    public int update(User user) {
        return userMapper.updateByPrimaryKey(user);
    }

    /**
     * 查询单个 * * @param id
     */
    @Override
    public User selectById(int id) {
        return userMapper.selectByPrimaryKey(id);
    }	
}