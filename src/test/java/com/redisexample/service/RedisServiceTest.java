package com.redisexample.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import com.redisexample.entity.Employee;

@SpringBootTest // used for integration testing
//@RunWith(SpringRunner.class)    	//it will be used for the Unit testing
public class RedisServiceTest {

	@Autowired
	private RedisService redisService;
	
	@Autowired
    private RedisTemplate<String, String> redisTemplate;

	@Test
	@Disabled
	public void testSaveRecord() {

		Employee emp = new Employee();
		emp.setId("1");
		emp.setName("Krish");
		emp.setAge(32);
		emp.setCity("Hyderabad");
		int ttlInSeconds = 120;

		String KEY = "TEST-ENVIRONMENT-" + emp.getId() + "" + emp.getName();
		String saveRecord = redisService.saveRecord(KEY, emp.getId(), emp.getName(), String.valueOf(emp.getAge()),
				emp.getCity(), ttlInSeconds);

		System.out.println("saveRecord : " + saveRecord);
		assertTrue(!saveRecord.isEmpty());

	}

	@Test
	public void testSaveSerializedEmployeeData() {
		Employee emp = new Employee();
		emp.setId("1");
		emp.setName("Krish");
		emp.setAge(32);
		emp.setCity("Hyderabad");

		String KEY = "TEST-ENVIRONMENT-" + emp.getId() + "" + emp.getName();

		String saveRecord = redisService.saveSerializedEmployeeData(KEY, emp);

		System.out.println("saveRecord : " + saveRecord);
		assertTrue(!saveRecord.isEmpty());

	}
	
	@Disabled
	@Test
	public void testinsertListOfEmployees() {
		List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("1", "John", 30,"Hyderabad"));
        employees.add(new Employee("2", "Jane", 35,"Bangalore"));

        redisService.insertListOfEmployees(employees);

        assertEquals("John", redisTemplate.opsForHash().get("employees:1", "name"));

	}

}
