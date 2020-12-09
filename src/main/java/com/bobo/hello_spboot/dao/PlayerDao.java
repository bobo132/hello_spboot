package com.bobo.hello_spboot.dao;

import com.bobo.hello_spboot.dao.mapper.PlayerMapper;
import com.bobo.hello_spboot.entity.Player;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class PlayerDao {

    @Resource
    private PlayerMapper playerMapper;

    public List<Player> listPlayer() {
        return playerMapper.listPlayer();
    }



}
