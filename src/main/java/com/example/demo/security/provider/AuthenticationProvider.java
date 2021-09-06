package com.example.demo.security.provider;

import com.example.demo.model.request.LoginRequest;
import com.example.demo.model.response.AuthResponse;

public abstract class AuthenticationProvider {

	public abstract AuthResponse signin(LoginRequest request);
}
