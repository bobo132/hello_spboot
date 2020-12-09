package com.bobo.hello_spboot.service;

import com.bobo.hello_spboot.dao.UserDao;
import com.bobo.hello_spboot.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    @Resource
    private UserDao userDao;

    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    public List<User> getUserList() {
        return userDao.getUserList();
    }

    public List<User> getUserList(int page, int size) {
        return userDao.getUserList(page, size);
    }

    public int addUser(User user) {
        return userDao.addUser(user);
    }
}
