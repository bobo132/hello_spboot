package com.bobo.hello_spboot.service;

import com.bobo.hello_spboot.dao.PlayerDao;
import com.bobo.hello_spboot.entity.Player;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PlayerService {

    @Resource
    private PlayerDao playerDao;

    public List<Player> listPlayer() {
        return playerDao.listPlayer();
    }

}
