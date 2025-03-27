package com.volleyverse.users_service.test_service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.volleyverse.users_service.dto.request.UserRegisterRequest;
import com.volleyverse.users_service.repository.UserRepository;
import com.volleyverse.users_service.service.imp.AuthServiceImp;

/**
 * The tests will be name with this structure: nameMethod_functionalitySuccess or nameMethod_functionalityFailure¿Why?
 * This class will verify that the register method will work in the following situations:
 * @author Ricardo Marín Esteban
 * @version 1.0
 * @since 2025
 */
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class RegisterUserTest {
	
	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private AuthServiceImp authServiceImp;
	
	private UserRegisterRequest user;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		this.user = new UserRegisterRequest("johndoe@example.com", "JohnDoe1!", "John Doe");
	}
	
	//This part test the email validation

	/**
	 * @section Email Validation:
	 * The email has to have the next structure: name@mail.domain.
	 * The name is the name of the email that user introduce when the user create it.
	 * The mail is the type of mail, for example: "gmail", "yahoo" or "hotmail".
	 * The domain is the last part of the email, it can be "com" or "es".
	 */
	
	/**
	 * @section TestID: TUS_01
	 * This test will register the default user John Doe with a valid email and password. It has to work.
	 */
	
	@Test
	void register_registerSuccess() {
		String response = authServiceImp.register(user);
		
		assertEquals("El usuario ha sido registrado con éxito", response);
	}
	
	/** 
	 * @section TestID: TUS_02
	 * This test will try to register the default user John Doe but the email doesn't have the "@". It can't work.
	 */
	
	@Test
	void register_registerFailureEmailWithoutAt() {
		user.setEmail("johndoeexample.com");
		String response = authServiceImp.register(user);
		
		assertEquals("El email no es válido, por favor revíselo.", response);
	}
	/**
	 * @section TestID: TUS_03
	 * This test will try to register the default user John Doe but the email doesn't have the ".". It can't work.
	 */
	@Test
	void register_registerFailureEmailWithoutDot() {
		user.setEmail("johndoe@examplecom");
		String response = authServiceImp.register(user);
		
		assertEquals("El email no es válido, por favor revíselo.", response);
	}
	
	/**
	 * @section TestID: TUS_04
	 * This test will try to register the default user John Doe but the email doesn't have the domain. It can't work.
	 */
	@Test
	void register_registerFailureEmailWithoutDomain() {
		user.setEmail("johndoe@example.");
		String response = authServiceImp.register(user);
		
		assertEquals("El email no es válido, por favor revíselo.", response);
	}
	
	/**
	 * @section TestID: TUS_05
	 * This test will try to register the default user John Doe but the email doesn't have the name. It can't work.
	 */
	@Test 
	void register_registerFailureEmailWithoutName() {
		user.setEmail("@example.com");
		String response = authServiceImp.register(user);
		
		assertEquals("El email no es válido, por favor revíselo.", response);
	}
	
	/**
	 * @section TestID: TUS_06
	 * This test will try to register the default user John Doe but the email doesn't have the mail. It can't work.
	 */
	@Test
	void register_registerFailureEmailWithoutMail() {
		user.setEmail("johndoe@.com");
		String response = authServiceImp.register(user);
		
		assertEquals("El email no es válido, por favor revíselo.", response);
	}
	
	/**
	 * @section TestID: TUS_07
	 * This test will register the default user John Doe, and will try to register another user with the same email. It can't work.
	 */
	@Test 
	void register_registerFailureEmailAlreadyExists() {
		UserRegisterRequest user2 = new UserRegisterRequest("johndoe@example.com", "SamLas1!", "Sam Las");
		authServiceImp.register(user);
		String response = authServiceImp.register(user2);
		
		assertEquals("El email ya está en uso, por favor escoja otro.", response);
	}
	
	//This part test the password validation
	
	/**
	 * @section Password Validation:
	 * - The password has to have minimum 7 characters.
	 * - The password has to have minimum one number.
	 * - The password has to have minimum one capital and one lower case 
	 * - The password has to have minimum one special character
	 */
	
	/**
	 * @section TestID: TUS_08
	 * This test will try to register the default user John Doe but the password doesn't have the more than 7 characters. It can't work.
	 */
	@Test
	void register_registerFailurePasswordNotLongEnough() {
		user.setPassword("JoD1!");
		String response = authServiceImp.register(user);
		
		assertEquals("La contraseña tiene que tener mínimo 7 caracteres.", response);
	}
	
	/**
	 * @section TestID: TUS_09
	 * This test will try to register the default user John Doe but the password doesn't have one or more numbers. It can't work.
	 */
	@Test 
	void register_registerFailurePasswordWithoutNumber() {
		user.setPassword("JohnDoe!");
		String response = authServiceImp.register(user);
		
		assertEquals("La contraseña tiene que tener mínimo un número.", response);
	}
	
	/**
	 * @section TestID: TUS_10
	 * This test will try to register the default user John Doe but the password doesn't have one or more capital letters. It can't work.
	 */
	@Test
	void register_registerFailurePasswordWithoutMayus() {
		user.setPassword("johndoe1!");
		String response = authServiceImp.register(user);
		
		assertEquals("La contraseña tiene que tener mínimo una mayúscula.", response);
	}
	
	/**
	 * @section TestID: TUS_11
	 * This test will try to register the default user John Doe but the password doesn't have the one or more lower case letters. It can't work.
	 */
	@Test
	void register_registerFailurePasswordWithoutMinus() {
		user.setPassword("JOHNDOE1!");
		String response = authServiceImp.register(user);
		
		assertEquals("La contraseña tiene que tener mínimo una minúscula.", response);
	}
	
	/**
	 * @section TestID: TUS_12
	 * This test will try to register the default user John Doe but the password doesn't have one or more special character. It can't work.
	 */
	@Test
	void register_registerFailurePasswordWithoutEspecialChar() {
		user.setPassword("JohnDoe1");
		String response = authServiceImp.register(user);
		
		assertEquals("La contraseña tiene que tener mínimo un caracter especial.", response);
	}

}
