package com.github.julioevencio.apitask.dto.user;

import java.util.UUID;

import com.github.julioevencio.apitask.dto.ResponseDTO;

public class UserResponseDTO extends ResponseDTO {

	private static final long serialVersionUID = 1L;

	private final UUID id;
	private final String username;
	private final String email;

	public UserResponseDTO(UUID id, String username, String email) {
		this.id = id;
		this.username = username;
		this.email = email;
	}

	public UUID getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

}
