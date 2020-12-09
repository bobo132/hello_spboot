package com.bobo.hello_spboot.util;


import cn.hutool.core.collection.CollUtil;
import com.bobo.hello_spboot.entity.Player;
import net.sf.cglib.beans.BeanMap;
import org.apache.commons.beanutils.BeanUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Map工具类, 可提供以下功能
 *
 * 1. JavaBean 和 Map互转
 *
 */


public class MapUtil {

    // 将对象转为 map
    public static <T> Map<String, Object> bean2Map(T bean) {
        Map<String, Object> map = new HashMap<>();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key + "", beanMap.get(key));
            }
        }
        return map;
    }

    /**
     * map -> javabean
     *  注意: 只标注了 @Data注解的 javabean无法转换成功, 参考 https://blog.csdn.net/qq_36599564/article/details/94736071
     *
     * @param map map
     * @param clazz javabean type
     * @param <T> 泛型方法标识
     * @return javabean
     */
    public static <T> T map2Bean(Map<String, Object> map, Class<T> clazz) {
        if (CollUtil.isNotEmpty(map)) {
            try {
                T bean = clazz.newInstance();
                // BeanUtils实现
                // BeanUtils.populate(bean, map);

                // BeanMap实现
                BeanMap beanMap = BeanMap.create(bean);
                beanMap.putAll(map);

                return bean;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     * Map<Object, Object>  ->  Map<String, Object>
     */
    public static Map<String, Object> convertStringKeyMap(Map<Object, Object> map) {
        Map<String, Object> targetMap = new HashMap<>();
        if (CollUtil.isNotEmpty(map)) {
            for (Map.Entry<Object, Object> entry : map.entrySet()) {
                targetMap.put(String.valueOf(entry.getKey()), entry.getValue());
            }
        }
        return targetMap;
    }




    public static void main(String[] args) {

        Player player = new Player(1, "张三", "zs@boke.com", "123456");

        Map<String, Object> map = MapUtil.bean2Map(player);
        System.out.println(map);

        Player bean = MapUtil.map2Bean(map, Player.class);
        System.out.println(bean);

    }




}
