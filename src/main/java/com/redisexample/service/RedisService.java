package com.redisexample.service;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.redisexample.entity.Employee;
import com.redisexample.util.RedisUtil;

@Service
public class RedisService {

	private static final Logger log = LoggerFactory.getLogger(RedisService.class);

	// Used Redis Insight for UI

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	@Value("${lua.script.filePath}")
	private String filePath;
	
	@Value("${lua.script.serializedEmployeePath}")
	private String serializedEmployeePath;
	
	@Value("${lua.script.insertListOfEmployeePath}")
	private String insertListOfEmployeePath;
	

	public void setHelloWorldKey() {
		String KEY = "Hello";
		String VALUE = "Hello-World! Redis..";
		long timeout = 2;
		TimeUnit timeUnit = TimeUnit.MINUTES;

		stringRedisTemplate.opsForValue().set(KEY, VALUE, timeout, timeUnit);
	}

	public String getHelloWorldValue() {
		return redisTemplate.opsForValue().get("hello");
	}

	public void fromLuaScript() {
		String[] keys = { "one", "two" };
		String[] values = { "one-value", "two-value" };
		log.info("unwanted " + keys + "" + values);

//		luaScriptService.executeLuaScript(keys, values);
	}

	public String saveRecord(String key, String id, String name, String age, String city,int ttlInSeconds) {
		String luaScript = RedisUtil.readLuaScript(filePath);

		return redisTemplate.execute(new DefaultRedisScript<String>(luaScript, String.class), 
							Collections.singletonList(key), 
									id, 
									name,
									age,
									city,
									String.valueOf(ttlInSeconds)); 
	}
	
	public String saveSerializedEmployeeData(String key, Employee employee) {
		String serializedEmployee = null;

		try { serializedEmployee = new ObjectMapper().writeValueAsString(employee);
		} catch (Exception e) {
			log.info("Unable to serialize the employee " + employee.toString());
		}
		String luaScript = RedisUtil.readLuaScript(serializedEmployeePath);

		return redisTemplate.execute(new DefaultRedisScript<>(luaScript, String.class), Collections.singletonList(key),
				serializedEmployee);
    }
	
	public String insertListOfEmployees(String key, String serializedListOfemployees) {
		String luaScript = RedisUtil.readLuaScript(insertListOfEmployeePath);

		String result = redisTemplate.execute(new DefaultRedisScript<>(luaScript, String.class),Collections.singletonList(key),serializedListOfemployees);
		return result;
			
	}

}





