/**  
 * @Title: aaa.java
 * @Package com.util
 * @Description: TODO
 * @author 赵帅帅
 * @date 2018年8月15日
 */
package com.util;

import redis.clients.jedis.Jedis;

/**
 * ClassName: aaa 
 * @Description: TODO items
 * @author 赵帅帅
 * @date 2018年8月15日
 */
public class aaa {
	public String addStringValue(String key, String value, int expireSeconds) {

        String result = null;
        try {
           jedisManager a = new jedisManager();
            Jedis resource = a.getResource();
            resource.sadd("44", "66");
            result = resource.get("44");
            if (expireSeconds != 0) {
                //EXPIRE key seconds 为给定 key 设置生存时间，当 key 过期时(生存时间为 0 )，它会被自动删除。
                
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return result;
    }
}
