package com.redisexample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.redisexample.service.RedisService;

@RestController
public class RedisController {

	@Autowired
	private RedisService redisService;

	@PostMapping("/set")
	@ResponseStatus(HttpStatus.OK)
	public String setHelloWorldKey() {
//		redisService.setHelloWorldKey();
		redisService.fromLuaScript();
		return "Saved";
	}

	@GetMapping("/get")
	@ResponseStatus(HttpStatus.OK)
	public String getHelloWorldValue() {
		return redisService.getHelloWorldValue();
	}
	
	

}
