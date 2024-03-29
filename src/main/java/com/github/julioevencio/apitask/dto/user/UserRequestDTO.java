package com.github.julioevencio.apitask.dto.user;

import java.io.Serializable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRequestDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Invalid username")
	@Size(max = 20, min = 1, message = "The username must be between 1 and 20 characters long")
	private final String username;

	@Email(message = "Invalid e-mail")
	@Size(max = 100, min = 1, message = "The e-mail must be between 1 and 100 characters long")
	private final String email;

	@NotBlank(message = "Invalid password")
	@Size(max = 20, min = 1, message = "The password must be between 1 and 20 characters long")
	private final String password;

	public UserRequestDTO(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

}
