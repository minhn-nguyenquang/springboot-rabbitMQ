package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.constant.ApiVersion;
import com.example.demo.model.request.LoginRequest;
import com.example.demo.model.response.AuthResponse;
import com.example.demo.service.AuthService;

@RestController
@RequestMapping(value = ApiVersion.API_V1 + "/auth")
public class AuthController {

	@Autowired
	private AuthService authService;
	
	@PostMapping(value = "/signin")
	public ResponseEntity<AuthResponse> index(@RequestBody LoginRequest request) {
	    return ResponseEntity.ok(authService.signin(request));
	}
	
	
}
