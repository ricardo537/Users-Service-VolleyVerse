package com.volleyverse.users_service.controller.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
		String response = authServiceImp.register(userRegisterRequest);
		HttpStatus status = null;
		
		switch (response) {
			case "El usuario ha sido registrado con éxito.": {
				status = HttpStatus.CREATED;
				break;
			}
			case "No se ha podido registrar el usuario.": {
				status = HttpStatus.INTERNAL_SERVER_ERROR;
				break;
			}
			case "El email ya está en uso, por favor escoja otro.": {
				status = HttpStatus.CONFLICT;
				break;
			}
			default: {
				status = HttpStatus.BAD_REQUEST;
				break;
			}
		}
		
		return new ResponseEntity<String>(response, status);
	}
	
}
