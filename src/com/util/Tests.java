/**  
 * @Title: Tests.java
 * @Package com.util
 * @Description: TODO
 * @author 赵帅帅
 * @date 2018年8月15日
 */
package com.util;

import redis.clients.jedis.Jedis;

/**
 * ClassName: Tests
 * 
 * @Description: TODO items
 * @author 赵帅帅
 * @date 2018年8月15日
 */
public class Tests {

	/**
	 * @Description: TODO
	 * @param @param args
	 * @return void
	 * @throws
	 * @author 赵帅帅
	 * @date 2018年8月15日
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Jedis j = new Jedis();
		j.set("zhao11", "shuai");
		System.out.println(j.get("zhao11"));
	}

}
