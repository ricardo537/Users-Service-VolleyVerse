package com.volleyverse.users_service.controller.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.volleyverse.users_service.controller.AuthController;
import com.volleyverse.users_service.dto.request.UserRegisterRequest;
import com.volleyverse.users_service.service.imp.AuthServiceImp;

@RestController
@RequestMapping("/volleyverse/api/auth")
public class AuthControllerImp implements AuthController {

	@Autowired
	private AuthServiceImp authServiceImp;

	@Override
	public ResponseEntity<String> register(UserRegisterRequest userRegisterRequest) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
