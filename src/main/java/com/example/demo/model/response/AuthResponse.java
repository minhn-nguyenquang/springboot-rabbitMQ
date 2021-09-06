package com.example.demo.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AuthResponse {

	@JsonProperty("access_token")
	private String accessToken;
}
