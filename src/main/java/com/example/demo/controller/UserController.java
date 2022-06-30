package com.example.demo.controller;

import java.util.List;
import static java.util.stream.Collectors.toList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.User;
import com.example.demo.dto.UserCreationDto;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserIdDto;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
	
	
	private final UserService userService;
	private final RoleService roleService;
	private final UserMapper mapper;
	
	@GetMapping
	@ResponseBody
	public List<UserDto> getUsers() {
		return userService.findAll()
				.stream()
				.map(mapper::toDto)
				.collect(toList());
	}
	
	@PostMapping
	@ResponseBody
	public UserIdDto create(@RequestBody UserCreationDto userDto) {
		
		User user = mapper.toUser(userDto);
		
		userDto.getRoles()
			.stream()
			.map(roleService::getOrCreate)
			.forEach(user::addRole);
		
		userService.save(user);
		
		return new UserIdDto(user.getId());
		
	}

}
