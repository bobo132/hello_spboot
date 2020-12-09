package com.bobo.hello_spboot;


import com.bobo.hello_spboot.entity.Player;
import com.bobo.hello_spboot.service.PlayerService;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class PlayerServiceTest extends BaseTest {

    @Resource
    private PlayerService playerService;


    @Test
    public void f1() {
        List<Player> playerList = playerService.listPlayer();
        System.out.println("playerList: " + playerList);
    }


}
