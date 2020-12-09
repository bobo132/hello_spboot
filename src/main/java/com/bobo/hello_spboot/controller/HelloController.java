package com.bobo.hello_spboot.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String say() {
        return "hello";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        String result = "username: " + username + ", password: " + password;
        System.out.println("login: " + result);
        return result;
    }


}
