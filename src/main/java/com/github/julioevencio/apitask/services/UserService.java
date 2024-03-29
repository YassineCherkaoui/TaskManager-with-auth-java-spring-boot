package com.github.julioevencio.apitask.services;

import java.util.List;

import com.github.julioevencio.apitask.dto.security.LoginRequestDTO;
import com.github.julioevencio.apitask.dto.security.TokenResponseDTO;
import com.github.julioevencio.apitask.dto.user.UserRequestDTO;
import com.github.julioevencio.apitask.dto.user.UserResponseDTO;

public interface UserService {

	UserResponseDTO register(UserRequestDTO dto);

	TokenResponseDTO login(LoginRequestDTO dto);

	UserResponseDTO me();

	List<UserResponseDTO> findAll();

}
