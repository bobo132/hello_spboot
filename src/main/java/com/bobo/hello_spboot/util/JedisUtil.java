package com.bobo.hello_spboot.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class JedisUtil {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;


    // -----------  各类型通用 ---------------
    /**
     * 指定缓存失效时间
     *
     * @param key key
     * @param time 过期时间(秒)
     * @return res
     */
    public Boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取 key的过期时间
     *
     * @param key key
     * @return 过期时间, 单位秒
     *  -1代表永久有效
     *  -2代表key不存在
     */
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断 key是否存在
     *
     * @param key key
     * @return bool res
     */
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }


    /**
     * 删除缓存
     *
     * @param keys key arr
     */
    public void del(String... keys) {
        if (keys != null && keys.length > 0) {
            redisTemplate.delete(CollUtil.newArrayList(keys));
        }
    }




    // -----------  string类型  ----------

    /**
     * 将键值对放入缓存
     *
     * @param key key
     * @param value value
     * @return bool res
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将键值对放入缓存, 可指定过期时间
     *
     * @param key 键
     * @param value 值
     * @param expireTime 过期时间, 单位秒
     * @return bool res
     */
    public boolean set(String key, Object value, long expireTime) {
        try {
            redisTemplate.opsForValue().set(key, value, expireTime, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 从缓存中获取值, 获得的 value类型和放入时的类型一致
     *
     * @param key key
     * @return bool res
     */
    public Object get(String key) {
        return StrUtil.isNotEmpty(key) ? redisTemplate.opsForValue().get(key) : null;
    }

    /**
     * 从缓存中获取值, 可指定value类型
     *
     * @param key key
     * @param clazz value类型
     * @param <T> 泛型方法
     * @return 类型转换之后的对象
     */
    public <T> T get(String key, Class<T> clazz) {
        try {
            return StrUtil.isNotEmpty(key) ? (T) redisTemplate.opsForValue().get(key) : null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 对指定key的值递增  (value += addValue)
     *
     * @param key key
     * @param addValue 递增量
     * @return
     */
    public Long incr(String key, int addValue) {
        if (addValue < 0)
            throw new RuntimeException("递增因子必须大于0");
        return redisTemplate.opsForValue().increment(key, addValue);
    }

    /**
     * 对指定key的值递减  (value -= reduceValue)
     *
     * @param key key
     * @param reduceValue 递减量
     * @return
     */
    public Long decr(String key, int reduceValue) {
        if (reduceValue < 0)
            throw new RuntimeException("递减因子必须大于0");
        return redisTemplate.opsForValue().decrement(key, reduceValue);
    }


    // -----------  hash类型  start  ----------

    /**
     * 创建一个 hash类型缓存
     *
     * @param key key
     * @param map map
     * @return bool res
     */
    public boolean hmset(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 方法同上, 但可以指定过期时间 (单位:秒)
     */
    public boolean hmset(String key, Map<String, Object> map, long expireTime) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            expire(key, expireTime);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 根据对象创建一个 hash类型缓存, 字段值为对象属性名
     *
     * @param key key
     * @param obj 对象
     * @return bool res
     */
    public boolean hmset(String key, Object obj) {
        Map<String, Object> map = MapUtil.bean2Map(obj);
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 向 hash表中放入数据, 如果不存在将创建建
     *
     * @param key key
     * @param item hash表字段名
     * @param itemValue hash表中该字段的值
     * @return bool res
     */
    public boolean hset(String key, String item, Object itemValue) {
        try {
            redisTemplate.opsForHash().put(key, item, itemValue);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 获取 hash类型数据
     *
     * @param key key
     * @param item hash field name
     * @return item value
     */
    public Object hget(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 获取 key对应的 hash类型的所有键值
     *
     * @param key key
     * @return map value
     */
    public Map<Object, Object> hmget(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 获取 key对应的 hash类型的所有键值, 以对象形式接收
     *
     * @param key key
     * @return map value
     */
    public <T> T hmget(String key, Class<T> clazz) {
        Map<Object, Object> map = redisTemplate.opsForHash().entries(key);
        return MapUtil.map2Bean(MapUtil.convertStringKeyMap(map), clazz);
    }




    /**
     * 删除哈希表中的值
     *
     * @param key 键
     * @param item item项
     */
    public void hdel(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * 判断哈希表中是否有该项的值
     *
     * @param key key
     * @param item item
     * @return bool res
     */
    public boolean hHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * hash递增, 如果不存在, 就会创建一个, 并把新增之后的值返回
     *
     * @param key key
     * @param item item
     * @param addValue 增加的值
     * @return 增加之后的值
     */
    public double hincr(String key, String item, double addValue) {
        return redisTemplate.opsForHash().increment(key, item, addValue);
    }

    /**
     * hash递增, 如果不存在, 就会创建一个, 并把新增之后的值返回
     *
     * @param key key
     * @param item item
     * @param addValue 增加的值
     * @return 增加之后的值
     */
    public double hdecr(String key, String item, double addValue) {
        return redisTemplate.opsForHash().increment(key, item, -addValue);
    }


    // -----------  hash类型  end  ----------







    // -----------  set类型  start  ----------

    /**
     * 将数据放入 set缓存
     *
     * @param key key
     * @param values 多个对象
     * @return 成功个数
     */
    public Long sSet(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }

    /**
     * 将数据放入 set缓存, 可指定过期时间, 单位秒
     *
     * @param key key
     * @param values 多个对象
     * @return 成功个数
     */
    public Long sSet(String key, long expireTime, Object... values) {
        try {
            Long successCount = redisTemplate.opsForSet().add(key, values);
            if (expireTime > 0)
                expire(key, expireTime);
            return successCount;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }

    /**
     * 根据key, 获取 set集合
     *
     * @param key key
     * @return set
     */
    public Set<Object> sGet(String key) {
        Set<Object> set = new HashSet<>();
        try {
            set = redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return set;
    }

    /**
     * 根据key, 获取 set集合, 可指定强制类型转换
     *
     * @param key key
     * @param clazz obj的类型
     * @param <T> 泛型方法表示
     * @return set
     */
    public <T> Set<T> sGet(String key, Class<T> clazz) {
        Set<T> set = new HashSet<>();
        try {
            set = (Set<T>) redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return set;
    }

    /**
     * 判断某个值是否在 set中, 在的话返回 true
     *
     * @param key set集合的 key
     * @param value value
     * @return bool res
     */
    public Boolean sHasKey(String key, Object value) {
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取 set缓存的个数
     *
     * @param key key
     * @return size
     */
    public Long sSize(String key) {
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }

    /**
     * 移除 set集合中的元素
     *
     * @param key key
     * @param values 1个或多个值
     * @return 成功移除的个数
     */
    public Long sRemove(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().remove(key, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }

    // -----------  set类型  end  ----------


    // -----------  list类型  start  ----------

    /**
     * 向 list中添加数据, 如果不存在则创建
     *
     * @param key key
     * @param value 项
     * @return list中元素的个数
     */
    public Long lSet(String key, Object value) {
        try {
            return redisTemplate.opsForList().rightPush(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }

    /**
     * 向 list中添加多个数据, 如果不存在则创建
     *
     * @param key key
     * @param values 多个值
     * @return list中元素的个数
     */
    public Long lSet(String key, Object... values) {
        try {
            return redisTemplate.opsForList().rightPushAll(key, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }

    /**
     * 向l ist中添加数据
     *
     * @param key key
     * @param value 项
     * @param expireTime 过期时间, 单位秒
     * @return list中元素的个数
     */
    public Long lSet(String key, Object value, long expireTime) {
        try {
            Long size = redisTemplate.opsForList().rightPush(key, value);
            if (expireTime > 0)
                expire(key, expireTime);
            return size;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }

    /**
     * 获取 list集合中的内容  (0, -1 表示整个集合的数据)
     *
     * @param key key
     * @param start 起始索引, 包含
     * @param end 结束索引, 不包含
     * @return list
     */
    public List<Object> lGet(String key, long start, long end) {
        List<Object> list = new ArrayList<>();
        try {
            list = redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 获取 list集合中的内容, 可指定强制类型转换
     *
     * @param key key
     * @param start 起始索引, 包含
     * @param end 结束索引, 不包含
     * @return list
     */
    public <T> List<T> lGet(String key, long start, long end, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        try {
            list = (List<T>) redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 获取 list指定位置的元素值
     *
     * @param key key
     * @param index 索引
     * @return 元素值
     */
    public Object lGetByIndex(String key, long index) {
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取list中的元素个数
     * @param key
     * @return
     */
    public Long lSize(String key) {
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }


    /**
     * [List] 修改某个索引位置的元素
     *
     * @param key key
     * @param index 索引
     * @param value 值
     * @return 操作结果
     */
    public Boolean lUpdate(String key, long index, Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * [List] 移除 n个值为 value的元素
     *
     * @param key key
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    public Long lRemove(String key, long count, Object value) {
        try {
            return redisTemplate.opsForList().remove(key, count, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }

    // -----------  list类型  end  ----------



    // -----------  ZSet类型  start  ----------

    /**
     * 向集合中增加一条记录, 如果这个值已存在, 这个值对应的权重将被设置为新的权重
     *
     * @param key key
     * @param score 权重
     * @param member 要加入的值
     * @return 状态码, true保存成功   false已存在, 但会更新score
     */
    public Boolean zAdd(String key, Object member, double score) {
        try {
            return redisTemplate.opsForZSet().add(key, member, score);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 返回集合中元素的个数
     *
     * @param key key
     * @return 元素个数, 如果返回0则表示元素不存在
     */
    public Long zCard(String key) {
        Long size = 0L;
        try {
            return redisTemplate.opsForZSet().zCard(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 获取指定权重区间的集合中元素个数
     *
     * @param key key
     * @param min minScore 包含
     * @param max maxScore 包含
     * @return 元素个数
     */
    public Long zCount(String key, double min, double max) {
        try {
            return redisTemplate.opsForZSet().count(key, min, max);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }


    /**
     * 获得 zset的元素个数
     *
     * @param key key
     * @return 元素个数
     */
    public int zLength(String key) {
        try {
            Set<Object> set = redisTemplate.opsForZSet().range(key, 0, -1);
            return set != null ? set.size() : 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 权重增加指定值, 如果给定的 member已存在
     *
     * @param key key
     * @param member 要插入的值
     * @param addScore 权重
     * @return 增加之后的权重
     */
    public Double zIncrby(String key, Object member, double addScore) {
        try {
            return redisTemplate.opsForZSet().incrementScore(key, member, addScore);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0d;
    }


    /**
     * 返回指定位置的元素集合, 0表示首个元素, -1表示末尾元素

     * @param key key
     * @param start 起始索引
     * @param end 结束索引
     * @return 元素集合
     */
    public Set<Object> zRange(String key, int start, int end) {
        try {
            return redisTemplate.opsForZSet().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new LinkedHashSet<>();
    }


    /**
     * 同上, 但可以指定强制类型转换
     */
    public <T> Set<T> zRange(Class<T> clazz, String key, int start, int end) {
        try {
            return (Set<T>) redisTemplate.opsForZSet().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new LinkedHashSet<>();
    }

    /**
     * 返回指定权重区间的元素集合
     *
     * @param key key
     * @param min 权重下限, 包含
     * @param max 权重上限, 包含
     * @return 元素集合
     */
    public Set<Object> zRangeByScore(String key, double min, double max) {
        try {
            return redisTemplate.opsForZSet().rangeByScore(key, min, max);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new LinkedHashSet<>();
    }

    /**
     * 同上, 但可以指定强制类型转换
     */
    public <T> Set<T> zRangeByScore(Class<T> clazz, String key, double min, double max) {
        try {
            return (Set<T>) redisTemplate.opsForZSet().rangeByScore(key, min, max);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new LinkedHashSet<>();
    }


    /**
     * 获取给定区间的元素, 按照权重从大到小排序
     *
     * @param key key
     * @param start 起始索引
     * @param end 结束索引
     * @return 元素集合
     */
    public Set<Object> zRevRange(String key, int start, int end) {
        try {
            return redisTemplate.opsForZSet().reverseRange(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new LinkedHashSet<>();
    }

    /**
     * 同上, 但可以指定强制类型转换
     */
    public <T> Set<T> zRevRange(Class<T> clazz, String key, int start, int end) {
        try {
            return (Set<T>) redisTemplate.opsForZSet().reverseRange(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new LinkedHashSet<>();
    }


    /**
     * 获取指定元素在集合中的位置, 集合按照score从小到大排序
     *
     * @param key key
     * @param member 元素
     * @return 位置索引
     */
    public Long zRank(String key, Object member) {
        try {
            return redisTemplate.opsForZSet().rank(key, member);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }

    /**
     * 获取指定元素在集合中的位置, score 倒序
     *
     * @param key key
     * @param member 元素
     * @return 位置索引
     */
    public Long zRevRank(String key, Object member) {
        try {
            return redisTemplate.opsForZSet().reverseRank(key, member);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }


    /**
     * 删除元素
     *
     * @param key key
     * @param member member
     * @return 返回1表示删除成功
     */
    public Long zRem(String key, Object member) {
        try {
            return redisTemplate.opsForZSet().remove(key, member);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }


    /**
     * 删除指定位置区间的元素
     *
     * @param key key
     * @param start start index
     * @param end end index
     * @return 删除的元素个数
     */
    public Long zRemRange(String key, int start, int end) {
        try {
            return redisTemplate.opsForZSet().removeRange(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }

    /**
     * 删除指定权重区间的元素
     *
     * @param key key
     * @param min 权重下限
     * @param max 权重上限
     * @return 删除的元素个数
     */
    public Long zRemRangeByScore(String key, double min, double max) {
        try {
            return redisTemplate.opsForZSet().removeRangeByScore(key, min, max);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }




    // -----------  ZSet类型  end  ----------











}
