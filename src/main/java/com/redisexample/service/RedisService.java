package com.redisexample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

	@Autowired
	private StringRedisTemplate redisTemplate;

	public void setHelloWorldKey() {
		// Setting the value of the key "hello" to "Hello, World!"
		redisTemplate.opsForValue().set("hello", "Hello, World!");
	}

	public String getHelloWorldValue() {
		// Retrieving the value of the key "hello"
		return redisTemplate.opsForValue().get("hello");
	}

}
