package com.volleyverse.users_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.volleyverse.users_service.dto.request.UserRegisterRequest;

public interface AuthController {
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody UserRegisterRequest userRegisterRequest);
}
