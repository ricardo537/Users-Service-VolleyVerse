package com.volleyverse.users_service.service.imp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.volleyverse.users_service.dto.request.UserRegisterRequest;
import com.volleyverse.users_service.entity.User;
import com.volleyverse.users_service.repository.UserRepository;
import com.volleyverse.users_service.service.AuthService;

/**
 * This class will manage the CRUD of the user's accounts, it's responsible of transform or validate the requests data, so that in the end the repository interacts with the database when if necessary.
 * @author Ricardo Marín Esteban
 * @version 1.0
 * @since 2025
 */
@Service
public class AuthServiceImp implements AuthService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * This method will validate the structure of the email and password. If all the data is valid the method will search for another user with that email, cause the email can be repeated.
	 * If all there isn't another user with that email, this will encode the password and save in the database.
	 * @section Email Validation:
	 * The email has to have the next structure: name@mail.domain.
	 * The name is the name of the email that user introduce when the user create it.
	 * The mail is the type of mail, for example: "gmail", "yahoo" or "hotmail".
	 * The domain is the last part of the email, it can be "com" or "es".
	 */
	public String register(UserRegisterRequest userRegisterRequest) {
		String emailRegex = "^[a-zA-Z0-9._]+@[a-zA-Z0.-]+\\.[a-zA-Z]{2,}$";
		
		if (!userRegisterRequest.getEmail().matches(emailRegex)) {
			return "El email no es válido, por favor revíselo.";
		} 
		
		String passwordMessage =  getErrorPasswordMessage(userRegisterRequest.getPassword());
		
		if (!passwordMessage.equals("")) {
			return passwordMessage;
		}
		
		Optional<User> userFound = this.userRepository.findByEmail(userRegisterRequest.getEmail());
		
		if (userFound.isPresent()) {
			return "El email ya está en uso, por favor escoja otro.";
		} 
		
		User user = userRegisterRequest.toUser();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		String passwordHash = passwordEncoder.encode(user.getPassword());
		User userSaved = this.userRepository.save(user);
		
		if (userSaved != null && userSaved.getId() == null) {
			return "No se ha podido registrar el usuario";
		} 
		return "El usuario ha sido registrado con éxito.";
	}
	
	/**
	 * This method validate that the password. In case the password is correct, this will return no message (""), in the other case this will return an error message with the criterion that does not meet.
	 * @section Password Validation:
	 * - The password has to have minimum 7 characters.
	 * - The password has to have minimum one number.
	 * - The password has to have minimum one capital and one lower case 
	 * - The password has to have minimum one special character
	 * @param password
	 * @return errorMessage
	 */
	private String getErrorPasswordMessage(String password) {
	    if (password.length() < 7) {
	        return "La contraseña tiene que tener mínimo 7 caracteres.";
	    }
	    if (!password.matches(".*[a-z].*")) {
	        return "La contraseña tiene que tener mínimo una minúscula.";
	    }
	    if (!password.matches(".*[A-Z].*")) {
	        return "La contraseña tiene que tener mínimo una mayúscula.";
	    }
	    if (!password.matches(".*[0-9].*")) {
	        return "La contraseña tiene que tener mínimo un número.";
	    }
	    if (!password.matches(".*[@#$%^&+=!].*")) {
	        return "La contraseña tiene que tener mínimo un caracter especial.";
	    }

	    return "";  
	}

}
