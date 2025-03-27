package com.volleyverse.users_service.test_repositories.user_repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.volleyverse.users_service.entity.User;
import com.volleyverse.users_service.repository.UserRepository;

/**
 * The tests will be name with this structure: nameMethod_functionalitySuccess or nameMethod_functionalityFailure¿Why?
 * This class will verify that the methods of searching the user via email or email and password will work in the following situations:
 * @author Ricardo Marín Esteban
 * @version 1.0
 * @since 2025
 */
@DataJpaTest
class SearchUserTest {
	
	@Autowired
	private UserRepository userRepository;
	
	private User user;
	
	/**
	 * Before all tests, we will register in the database a default user "John Doe" because for searching a user we need to have users in the database.
	 */
	@BeforeEach
	void setUp() {
		this.user = new User("johndoe@example.com", "JohnDoe1!", "John Doe");
		userRepository.save(this.user);
	}

	/**
	 * This test will search the user of John Doe via email, it has to found.
	 */
	@Test
	void findByEmail_foundSuccess() {
		Optional<User> userFound = this.userRepository.findByEmail("johndoe@example.com");
		
		assertTrue(userFound.isPresent());
		assertEquals("John Doe", userFound.get().getName());
	}
	
	/**
	 * This test will search user via email that it's not register in the database, so it can found anything.
	 */
	@Test
	void findByEmail_foundFailure() {
		Optional<User> userFound = this.userRepository.findByEmail("samlas@example.com");
		
		assertFalse(userFound.isPresent());
	}
	
	
	/**
	 * This test will search the user of John Doe via email and password, it has to found.
	 */
	/*
	@Test
	void findByEmailAndPassword_foundSuccess() {
		Optional<User> userFound = this.userRepository.findByEmailAndPassword("johndoe@example.com", "JohnDoe1!");
		
		assertTrue(userFound.isPresent());
		assertEquals("John Doe", userFound.get().getName());
	}*/
	
	/**
	 * This test will search user via email and password that email was registered but not with the same password we register the user in the database, so it can found anything.
	 */
	/*
	@Test
	void findByEmailAndPassword_foundFailure() {
		Optional<User> userFound = this.userRepository.findByEmailAndPassword("johndoe@example.com", "SamLas1!");
		
		assertFalse(userFound.isPresent());
	}*/

}
