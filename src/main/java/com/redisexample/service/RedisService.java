package com.redisexample.service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
	
	//Used Redis Insight for UI REDIS

	@Autowired
	private StringRedisTemplate redisTemplate;

	public void setHelloWorldKey() {
		String KEY = "Hello";
		String VALUE = "Hello-World! Redis..";
		long timeout = 2;
		TimeUnit timeUnit = TimeUnit.MINUTES;

		redisTemplate.opsForValue().set(KEY, VALUE, timeout, timeUnit);
	}

	public String getHelloWorldValue() {
		return redisTemplate.opsForValue().get("hello");
	}

}
