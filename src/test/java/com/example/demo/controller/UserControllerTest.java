package com.example.demo.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import com.example.demo.dao.RoleRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.dto.UserCreationDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import io.restassured.http.ContentType;
//import io.restassured.matcher.RestAssuredMatchers.*;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@LocalServerPort
	int port;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	

	@Test
	void testGetUsers() {
		userRepository.deleteAll();
		
		String roleName = "admin";
		Role admin = new Role(roleName);
		roleRepository.save(admin);
		
		String name = "User 1";
		User user = new User(name, "Test@123456", Collections.singletonList(admin));
		userRepository.save(user);
		
		given()
			.port(port)
		.when()
			.get("/users")
		.then()
			.statusCode(OK.value())
			.body("size()", is(1))
			.body("[0].name", equalTo(name))
			.body("[0].roles", hasItem(roleName));
			
			
		
	}

	@Test
	void testCreate() throws JsonProcessingException {
		
		UserCreationDto request = new UserCreationDto();
		request.setName("User 1");
		request.setPassword("Test@123456");
		request.setRoles(Collections.singletonList("admin"));
		
		given()
			.contentType(ContentType.JSON)
			.body(objectMapper.writeValueAsString(request))
		.when()
			.port(port)
			.post("/users")
		.then()
			.statusCode(OK.value())
			.body("id", notNullValue());
		
		
	}

}
