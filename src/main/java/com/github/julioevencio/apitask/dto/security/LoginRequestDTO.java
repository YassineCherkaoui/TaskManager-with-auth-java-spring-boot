package com.github.julioevencio.apitask.dto.security;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginRequestDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Invalid username")
	@Size(max = 20, min = 1, message = "The username must be between 1 and 20 characters long")
	private final String username;

	@NotBlank(message = "Invalid password")
	@Size(max = 20, min = 1, message = "The password must be between 1 and 20 characters long")
	private final String password;

	public LoginRequestDTO(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

}
