package com.volleyverse.users_service.test_service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * The tests will be name with this structure: nameMethod_functionalitySuccess or nameMethod_functionalityFailure¿Why?
 * This class will verify that the login method works in the following situations:
 * @author Ricardo Marín Esteban
 * @version 1.0
 * @since 2025
 */
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class LoginUserTest {
	
	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private UserService userService;
	
	private User userDefault;

	/**
	 * Before all test we make sure that default user John Doe exists.
	 */
	@BeforeEach
	void setUp() {
		userDefault = new User(null, "johndoe@example.com", "JohnDoe1!", "John Doe");
		
		userService.register(user);
	}

	/**
	 * This test will try to login with the user of John Doe, with the correct email and password.
	 */
	@Test
	void login_loginSuccess() {
		User userLog = new User(null, "johndoe@example.com", "JohnDoe1!", "John Doe");
		
		when(userRepository.findByEmailAndPassword(userLog.getEmail(), userLog.getPassword())).thenReturn(Optional.of(userDefault));
		
	}
	
	/**
	 * This test will try to login with a email that doesn't exists in the data base, so it can't login.
	 */
	@Test
	void login_loginFailureEmailNotExists() {
		
	}
	
	/**
	 * This test will try to login with the email of John Doe but a different password, so it can't login.
	 */
	@Test
	void login_loginFailurePasswordNotMatch() {
		
	}

}
