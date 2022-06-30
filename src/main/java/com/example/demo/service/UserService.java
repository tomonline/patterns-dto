package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.UserRepository;
import com.example.demo.domain.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository repository;

	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User save(User user) {
		return this.repository.save(user);
	}
}
