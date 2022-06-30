package com.example.demo.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {

	@Test
	@DisplayName("When User is Created, should encrypt Password")
	void whenUserIsCreated_ShouldEncryptPassword() {
		User user = new User("Test", "test", new ArrayList<>());

		assertEquals(user.encrypt("test"), user.getPassword());
		assertNotEquals(user.encrypt("Test"), user.getPassword());
	}

	@Test
	@DisplayName("When User is Created, should fail if name is null")
	void whenUserIsCreated_shouldFailIfNameIsNull() {
		assertThrows(NullPointerException.class, () -> 
			new User(null, "test", new ArrayList<>()));
	}

	@Test
	@DisplayName("When User is Created, should fail if password is null")
	void whenUserIsCreated_shouldFailIfPasswordIsNull() {
		assertThrows(NullPointerException.class, () -> 
			new User("Test", null, new ArrayList<>()));
	}

	@Test
	@DisplayName("When User is Created, should fail if roles is null")
	void whenUserIsCreated_shoudlFailIfRolesIsNull() {
		assertThrows(NullPointerException.class, () -> 
			new User("Test", "test", null));
	}

}
