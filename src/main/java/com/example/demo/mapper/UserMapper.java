package com.example.demo.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.dto.UserCreationDto;
import com.example.demo.dto.UserDto;

@Component
public class UserMapper {
	
	
	public UserDto toDto(User user) {
		String name = user.getName();
		
		List<String> roles = user
				.getRoles()
				.stream()
				.map(Role::getName)
				.collect(Collectors.toList());
		
		return new UserDto(name, roles);
	}
	
	public User toUser(UserCreationDto userDto) {
		return new User(userDto.getName(), userDto.getPassword(), new ArrayList<>());
	}

}
