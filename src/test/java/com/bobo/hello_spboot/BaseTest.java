package com.bobo.hello_spboot;


import com.bobo.hello_spboot.entity.Player;
import com.bobo.hello_spboot.service.PlayerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@MapperScan("com.bobo.hello_spboot.dao.mapper")
public class BaseTest {




}
