package com.volleyverse.users_service.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.volleyverse.users_service.dto.request.UserRegisterRequest;
import com.volleyverse.users_service.repository.UserRepository;
import com.volleyverse.users_service.service.AuthService;

@Service
public class AuthServiceImp implements AuthService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public String register(UserRegisterRequest userRegisterRequest) {
		return "";
	}
}
