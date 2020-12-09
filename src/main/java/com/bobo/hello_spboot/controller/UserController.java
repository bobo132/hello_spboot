package com.bobo.hello_spboot.controller;


import com.bobo.hello_spboot.entity.User;
import com.bobo.hello_spboot.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") int id) {
        return userService.getUserById(id);
    }

    @GetMapping("/list")
    public List<User> getUserList() {
        List<User> userList = userService.getUserList();
        System.out.println(userList);
        return userList;
    }

    @GetMapping("/pageList")
    public List<User> getUserList(@RequestParam("page") int page, @RequestParam("size") int size) {
        return userService.getUserList(page, size);
    }

    @PostMapping("/add")
    public int getUserList(@RequestParam("username") String username,
                                  @RequestParam("password") String password, @RequestParam("email") String email) {
        User user = new User().setUsername(username).setPassword(password).setEmail(email);
        return userService.addUser(user);
    }


}
