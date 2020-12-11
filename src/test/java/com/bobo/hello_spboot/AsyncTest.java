package com.bobo.hello_spboot;

import com.bobo.hello_spboot.entity.User;
import com.bobo.hello_spboot.service.UserService;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AsyncTest extends BaseTest {

    @Resource
    private UserService userService;


    @Test
    public void f1() throws IOException {
        userService.sayHelloAsync("张三");
        int read = System.in.read();   // 阻塞执行, 才能看到结果
    }

    @Test
    public void f2() {
        Future<User> future = userService.getUserByIdAsync(1);
        try {
            User user = future.get();   // 阻塞式地获取结果
            System.out.println("user: " + user);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}
