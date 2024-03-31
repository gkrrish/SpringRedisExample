package com.redisexample.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.redisexample.entity.Employee;

@SpringBootTest // used for integration testing
//@RunWith(SpringRunner.class)    	//it will be used for the Unit testing
public class RedisServiceTest {

	@Autowired
	private RedisService redisService;

	@Test
	public void testSaveRecord() {

		Employee emp = new Employee();
		emp.setId("1");
		emp.setName("Krish");
		emp.setAge(32);
		emp.setCity("Hyderabad");
		int ttlInSeconds = 120;

		String KEY = "TEST-ENVIRONMENT-2" + emp.getId() + "" + emp.getName();
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
	
	@Test
	public void testinsertListOfEmployees() throws JsonProcessingException {
		List<Employee> employees = new ArrayList<>();
		employees.add(new Employee("1", "John", 30, "Hyderabad"));
		employees.add(new Employee("2", "Jane", 35, "Bangalore"));

		String rValue = redisService.insertListOfEmployees("TEST-KEY",new ObjectMapper().writeValueAsString(employees));

		assertEquals("Employees inserted successfully", rValue);

	}

}
