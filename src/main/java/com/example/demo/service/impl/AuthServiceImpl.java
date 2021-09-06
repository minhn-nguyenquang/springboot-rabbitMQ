package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.request.LoginRequest;
import com.example.demo.model.response.AuthResponse;
import com.example.demo.security.provider.AuthenticationProvider;
import com.example.demo.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private AuthenticationProvider authenticationProvider;
	@Override
	public AuthResponse signin(LoginRequest request) {
		return authenticationProvider.signin(request);
	}

}
