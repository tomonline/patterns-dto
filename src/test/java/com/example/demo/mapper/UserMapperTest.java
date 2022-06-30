package com.example.demo.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.dto.UserCreationDto;
import com.example.demo.dto.UserDto;

class UserMapperTest {

	@Test
	void testToDto() {
		
		String name = "Test";
		String password = "test";
		Role admin = new Role("admin");
		List<String> expectedRoles = Collections.singletonList("admin");
		
//		Set<Role> roles = new HashSet<>();
		List<Role> roles = new ArrayList<>();
		roles.add(admin);
		
		User user = new User(name, password, roles);
		UserMapper mapper = new UserMapper();
		
		UserDto dto = mapper.toDto(user);
		
		assertEquals(name, dto.getName());
		assertEquals(expectedRoles, dto.getRoles());
		
		
	}

	@Test
	void testToUser() {
		
		String name = "Test";
		String password = "test";
		String role = "admin";
		
		
		UserCreationDto dto = new UserCreationDto();
		dto.setName(name);
		dto.setPassword(password);
		dto.setRoles(Collections.singletonList("admin"));
		
		User expectedUser = new User(name, password, new ArrayList<>());
		
		UserMapper mapper = new UserMapper();
		User user = mapper.toUser(dto);
		
		assertEquals(name, user.getName());
		assertEquals(expectedUser.getPassword(), user.getPassword());
		assertEquals(Collections.emptyList(), user.getRoles());
		
			
	}

}
