package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dao.RoleRepository;
import com.example.demo.domain.Role;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService {

	private final RoleRepository repository;
	
	public Role getOrCreate(String name) {
		Role role = repository.findOneByName(name);
		
		if (role == null) {
			role = new Role(name);
			repository.save(role);
		}
		
		return role;
	}
}
