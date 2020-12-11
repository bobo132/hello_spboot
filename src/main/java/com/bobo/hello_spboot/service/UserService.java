package com.bobo.hello_spboot.service;

import com.bobo.hello_spboot.dao.UserDao;
import com.bobo.hello_spboot.entity.User;
import org.springframework.data.redis.connection.FutureResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

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

    // 无返回值的异步方法
    @Async
    public void sayHelloAsync(String msg) {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("hello " + msg);
    }


    @Async
    public Future<User> getUserByIdAsync(int userId) {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        User user = getUserById(userId);
        return new AsyncResult<>(user);
    }


}
