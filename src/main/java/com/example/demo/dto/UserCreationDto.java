package com.example.demo.dto;

import java.util.List;

import lombok.Data;

@Data
public class UserCreationDto {
	
	private String name;
	private String password;
	private List<String> roles;

}
