package com.example.demo.security.provider;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.request.LoginRequest;
import com.example.demo.model.response.AuthResponse;

@Component
@Qualifier("keycloakProvider")
public class KeycloakProvider extends AuthenticationProvider {

	@Value("${keycloak-config.signin-url}")
	private String signinUrl;

	@Value("${keycloak.resource}")
	private String clientId;

	private static final String GRANT_TYPE_PASSWORD = "password";

	@Override
	public AuthResponse signin(LoginRequest loginRequest) {
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("client_id", clientId);
		map.add("username", loginRequest.getUsername());
		map.add("password", loginRequest.getPassword());
		map.add("grant_type", GRANT_TYPE_PASSWORD);

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

		ResponseEntity<AuthResponse> response = restTemplate.postForEntity(signinUrl, request, AuthResponse.class);
		return response.getBody();
	}

}
