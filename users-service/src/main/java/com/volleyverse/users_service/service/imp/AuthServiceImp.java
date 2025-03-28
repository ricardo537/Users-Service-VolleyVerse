package com.volleyverse.users_service.service.imp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.volleyverse.users_service.dto.request.UserRegisterRequest;
import com.volleyverse.users_service.entity.User;
import com.volleyverse.users_service.repository.UserRepository;
import com.volleyverse.users_service.service.AuthService;

@Service
public class AuthServiceImp implements AuthService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public String register(UserRegisterRequest userRegisterRequest) {
		String emailRegex = "^[a-zA-Z0-9._]+@[a-zA-Z0.-]+\\.[a-zA-Z]{2,}$";
		String passwordRegex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!])[A-Za-z\\d@#$%^&+=!]{7,}$";
		
		if (!userRegisterRequest.getEmail().matches(emailRegex)) {
			return "El email no es válido, por favor revíselo.";
		} 
		if (!userRegisterRequest.getPassword().matches(passwordRegex)) {
			return getWrongPasswordMessage(userRegisterRequest.getPassword());
		}
		
		Optional<User> userFound = this.userRepository.findByEmail(userRegisterRequest.getEmail());
		
		if (userFound.isPresent()) {
			return "El email ya está en uso, por favor escoja otro.";
		} 
		
		User user = userRegisterRequest.toUser();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User userSaved = this.userRepository.save(user);
		
		if (userSaved != null && userSaved.getId() == null) {
			return "No se ha podido registrar el usuario";
		} 
		return "El usuario ha sido registrado con éxito.";
	}
	
	private String getWrongPasswordMessage(String password) {
	    if (password.length() < 7) {
	        return "La contraseña tiene que tener mínimo 7 caracteres.";
	    }
	    if (!password.matches("[a-z]+")) {
	        return "La contraseña tiene que tener mínimo una minúscula.";
	    }
	    if (!password.matches("[A-Z]+")) {
	        return "La contraseña tiene que tener mínimo una mayúscula.";
	    }
	    if (!password.matches("[0-9]+")) {
	        return "La contraseña tiene que tener mínimo un número.";
	    }
	    if (!password.matches("[@#$%^&+=!]+")) {
	        return "La contraseña tiene que tener mínimo un caracter especial.";
	    }

	    return "";  
	}

}
