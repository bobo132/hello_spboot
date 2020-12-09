package com.bobo.hello_spboot;

import com.bobo.hello_spboot.entity.Player;
import com.bobo.hello_spboot.entity.User;
import com.bobo.hello_spboot.util.JedisUtil;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;

public class RedisTest extends BaseTest {

    @Resource
    private JedisUtil jedisUtil;


    @Test
    public void f1() {
        String key = "player";
        Player player = new Player();
        player.setUsername("zs");
        player.setPassword("123");

        jedisUtil.set(key, player);
        Player playerFromCache = jedisUtil.get(key, Player.class);
        System.out.println(playerFromCache);

    }


    @Test
    public void testString() {
        String prefix = "str:";
        String key = prefix + "name";
        String value = "张三";

//        jedisUtil.set(key, value);
        System.out.println(jedisUtil.getExpire(key));   // 过期时间: -1 即永久生效   -2 key不存在

        jedisUtil.expire(key, 30);
        System.out.println(jedisUtil.getExpire(key));   // 过期时间: 9s



    }

    @Test
    public void testHash() {

        String key = "hash:player";

//        Player player = new Player(1, "张三", "zs@boke.com", "123456");
//        jedisUtil.hmset(key, player);

//        Map<Object, Object> map = jedisUtil.hmget(key);
//        System.out.println(map);

//        Player bean = jedisUtil.hmget(key, Player.class);
//        System.out.println("bean: " + bean);

//        jedisUtil.hset(key, "username", "王五");
//        Player bean = jedisUtil.hmget(key, Player.class);
//        System.out.println("bean: " + bean);

//        double id = jedisUtil.hincr(key, "id", 2);
//        System.out.println("id: " + id);

//        boolean res = jedisUtil.hset(key, "age", 28);
//        System.out.println("res: " + res);

        boolean contains = jedisUtil.hHasKey(key, "aaa");
        System.out.println("res: " + contains);

    }


    @Test
    public void testSet() {

        String key = "set:player";

        Player p1 = new Player(1, "张三", "zs@boke.com", "123456");
        Player p2 = new Player(2, "李四", "ls@boke.com", "123456");
        Player p3 = new Player(3, "王五", "ww@boke.com", "123456");

        Long l = jedisUtil.sSet(key, p1, p2, p3, new User());
        System.out.println("long: " + l);


        Set<Object> objSet = jedisUtil.sGet(key);
        System.out.println(objSet);

        Set<Player> playerSet = jedisUtil.sGet(key, Player.class);
        System.out.println(playerSet);


    }


    @Test
    public void testList() {

        String key = "list:player";

//        Player p1 = new Player(1, "张三", "zs@boke.com", "123456");
//        Player p2 = new Player(2, "李四", "ls@boke.com", "123456");
//        Player p3 = new Player(3, "王五", "ww@boke.com", "123456");

        Player p4 = new Player(4, "赵六", "zl@boke.com", "123456");
        Player p5 = new Player(5, "马七", "mq@boke.com", "123456");
        Player p6 = new Player(6, "陈八", "cb@boke.com", "123456");

        Long aLong = jedisUtil.lSet(key, p4, p5);
        System.out.println("aLong: " + aLong);

    }


    @Test
    public void testZSet() {

        String key = "zset:player";

        Player p4 = new Player(4, "赵六", "zl@boke.com", "123456");
        Player p5 = new Player(5, "马七", "mq@boke.com", "123456");
        Player p6 = new Player(6, "陈八", "cb@boke.com", "123456");

//        jedisUtil.zAdd(key, p4, 40);
//        jedisUtil.zAdd(key, p5, 50);


//        Boolean res3 = jedisUtil.zAdd(key, p6, 65);     // 如果值不存在时, 返回true,    如果存在时, 更新score值, 返回false
//        System.out.println("res3: " + res3);

//        System.out.println("size: " + jedisUtil.zCard(key));
//        System.out.println("count: " + jedisUtil.zCount(key, 50, 65));
//        System.out.println("length: " + jedisUtil.zLength(key));


//        Double score = jedisUtil.zIncrby(key, p4, 2);
//        System.out.println("score: " + score);

//        System.out.println("object set: " + jedisUtil.zRange(key, 0, -1));

//        Set<Player> playerSet = jedisUtil.zRange(Player.class, key, 0, -1);
//        System.out.println("player set: " + playerSet);

//        Set<Object> objSet = jedisUtil.zRangeByScore(key, 42, 50);
//        System.out.println("objSet: " + objSet);

//        Set<Player> playerSet = jedisUtil.zRangeByScore(Player.class, key, 42, 50);
//        System.out.println("playerSet: " + playerSet);


//        System.out.println("p5 zRank: " + jedisUtil.zRank(key, p5));      // 1
//        System.out.println("p5 zRem: " + jedisUtil.zRem(key, p5));      // 1

//        System.out.println("p5 zRevRank: " + jedisUtil.zRevRank(key, p5));

//        jedisUtil.zAdd(key, p6, 60);
//        System.out.println("p6 zRank: " + jedisUtil.zRank(key, p6));
//        System.out.println("p6 zRevRank: " + jedisUtil.zRevRank(key, p6));

//        jedisUtil.zAdd(key, p4, 40);
//        jedisUtil.zAdd(key, p5, 50);
//        jedisUtil.zAdd(key, p6, 60);

//        System.out.println("删除的元素个数: " + jedisUtil.zRemRange(key, 0, 1));
//        System.out.println("删除的元素个数: " + jedisUtil.zRemRangeByScore(key, 51, 65));

//        System.out.println(jedisUtil.zRevRange(key, 0, -1));    // 6, 5, 4
//        System.out.println(jedisUtil.zRevRange(key, 0, 1));     // 6, 5

    }





}
