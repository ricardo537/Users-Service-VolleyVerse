package com.volleyverse.users_service.test_controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.net.ssl.SSLEngineResult.Status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.volleyverse.users_service.controller.imp.AuthControllerImp;
import com.volleyverse.users_service.dto.request.UserRegisterRequest;
import com.volleyverse.users_service.service.imp.AuthServiceImp;

/**
 * The tests will be name with this structure: nameMethod_functionalitySuccess or nameMethod_functionalityFailure
 * This class will verify that the end-points of AuthCotroller works in the following situations:
 * @author Ricardo Marín Esteban
 * @version 1.0
 * @since 2025
 */
@WebMvcTest(AuthControllerImp.class)
@ExtendWith(MockitoExtension.class)
class AuthControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AuthServiceImp authService;
	
	private ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * This test will try to register the default user John Doe. It has to work.
	 * @throws Exception
	 */
	@Test
	void register_registerSuccess() throws Exception {
		 UserRegisterRequest request = new UserRegisterRequest("johndoe@gmail.com", "JohnDoe1!", "John Doe");

	     when(authService.register(Mockito.any(UserRegisterRequest.class)))
	     	.thenReturn("El usuario ha sido registrado con éxito.");

	     mockMvc.perform(post("/volleyverse/api/auth/register")
	        .contentType(MediaType.APPLICATION_JSON)
	        .content(objectMapper.writeValueAsString(request))) 
	        .andExpect(status().isCreated()) 
	        .andExpect(jsonPath("$").value("El usuario ha sido registrado con éxito."));
	}
	
	/**
	 * This test will try to register a user with wrong data. It can't work.
	 * @throws Exception
	 */
	
	@Test
	void register_registerFailure() throws Exception {
		UserRegisterRequest request = new UserRegisterRequest("johndoe@.com", "JohnDoe1!", "John Doe");
		
		when(authService.register(Mockito.any(UserRegisterRequest.class)))
			.thenReturn("El email no es válido, por favor revíselo.");
		
		mockMvc.perform(post("/volleyverse/api/auth/register")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(request)))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$").value("El email no es válido, por favor revíselo."));
	}
	
	/**
	 * This test will try to login with the user John Doe. It has to work.
	 * @throws Exception
	 */
	/*
	@Test
	void login_loginSuccess() throws Exception {
		
	}
	
	/**
	 * This test will try to login with the user John Doe but with a wrong password. It can't work.
	 * @throws Exception
	 */
	/*
	@Test 
	void login_loginFailure() throws Exception {
		
	}*/

}
