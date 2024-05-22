package com.cherkaoui.yassine.apitask.services;

import java.util.List;

import com.cherkaoui.yassine.apitask.dto.security.LoginRequestDTO;
import com.cherkaoui.yassine.apitask.dto.security.TokenResponseDTO;
import com.cherkaoui.yassine.apitask.dto.user.UserRequestDTO;
import com.cherkaoui.yassine.apitask.dto.user.UserResponseDTO;

public interface UserService {

	UserResponseDTO register(UserRequestDTO dto);

	TokenResponseDTO login(LoginRequestDTO dto);

	UserResponseDTO me();

	List<UserResponseDTO> findAll();

}
