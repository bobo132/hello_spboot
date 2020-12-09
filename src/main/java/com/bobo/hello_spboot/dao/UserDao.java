package com.bobo.hello_spboot.dao;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.bobo.hello_spboot.dao.mapper.UserMapper;
import com.bobo.hello_spboot.entity.User;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class UserDao {

    @Resource
    private UserMapper userMapper;

    public User getUserById(int id) {
        return userMapper.selectById(id);
    }

    public List<User> getUserList() {
        return userMapper.selectList(new EntityWrapper<>());
    }

    public List<User> getUserList(int page, int size) {
        return userMapper.selectPage(new Page<User>(page, size), new EntityWrapper<>());
    }

    public int addUser(User user) {
        return userMapper.insert(user);
    }
}
