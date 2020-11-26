package com.bobo.hello_spboot.mapper;

import com.bobo.hello_spboot.entity.Player;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerMapper {

    List<Player> listPlayer();

    Player getPlayerById(int id);

    Player getPlayerByUserName(String username);

    int savePlayer(Player player);
}
