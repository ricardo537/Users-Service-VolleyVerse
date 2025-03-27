package com.volleyverse.users_service.service;

import com.volleyverse.users_service.dto.request.UserRegisterRequest;

public interface AuthService {
	
	public String register (UserRegisterRequest userRegisterRequest);
}
