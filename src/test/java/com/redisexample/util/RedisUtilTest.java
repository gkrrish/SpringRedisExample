package com.redisexample.util;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RedisUtilTest {

	@Value("${lua.script.filePath}")
	private String filePath;
	
	@Value("${lua.script.serializedEmployeePath}")
	private String serializedEmployeePath;
	
	@Value("${lua.script.insertListOfEmployeePath}")
	private String insertListOfEmployeePath;

	@Test
	public void testReadLuaScript() throws IOException {

		String actualScript = RedisUtil.readLuaScript(filePath);
		System.out.println("actualScript : " + actualScript);

		assertTrue(!actualScript.isEmpty());
	}
	
	@Test
	public void testserializedEmployeeReadLuaScript() throws IOException {

		String actualScript = RedisUtil.readLuaScript(serializedEmployeePath);
		System.out.println("actualScript : " + actualScript);

		assertTrue(!actualScript.isEmpty());
	}
	
	@Test
	public void testinsertListOfEmployeeReadLuaScript() throws IOException {

		String actualScript = RedisUtil.readLuaScript(insertListOfEmployeePath);
		System.out.println("actualScript : " + actualScript);

		assertTrue(!actualScript.isEmpty());
	}

}
