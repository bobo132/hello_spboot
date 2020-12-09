package com.bobo.hello_spboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bobo.hello_spboot.dao.mapper")
public class HelloSpbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloSpbootApplication.class, args);
    }

}
