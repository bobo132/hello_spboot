package com.bobo.hello_spboot.controller;


import com.bobo.hello_spboot.entity.Player;
import com.bobo.hello_spboot.service.PlayerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Resource
    private PlayerService playerService;

    @GetMapping("/list")
    public List<Player> getPlayerList() {
        return playerService.listPlayer();
    }

}
