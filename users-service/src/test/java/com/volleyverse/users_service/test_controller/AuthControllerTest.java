package com.volleyverse.users_service.test_controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The tests will be name with this structure: nameMethod_functionalitySuccess or nameMethod_functionalityFailure
 * This class will verify that the end-points of AuthCotroller works in the following situations:
 * @author Ricardo Marín Esteban
 * @version 1.0
 * @since 2025
 */
@WebMvcTest(AuthController.class)
@ExtendWith(MockitoExtension.class)
class AuthControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AuthService authService;
	
	private ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * This test will try to register the default user John Doe. It has to work.
	 * @throws Exception
	 */
	@Test
	void register_registerSuccess() throws Exception {
		fail("Not yet implemented");
	}
	
	/**
	 * This test will try to register a user with wrong data. It can't work.
	 * @throws Exception
	 */
	@Test
	void register_registerFailure() throws Exception {
		
	}
	
	/**
	 * This test will try to login with the user John Doe. It has to work.
	 * @throws Exception
	 */
	@Test
	void login_loginSuccess() throws Exception {
		
	}
	
	/**
	 * This test will try to login with the user John Doe but with a wrong password. It can't work.
	 * @throws Exception
	 */
	@Test 
	void login_loginFailure() throws Exception {
		
	}

}
