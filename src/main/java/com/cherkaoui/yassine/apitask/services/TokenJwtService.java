package com.cherkaoui.yassine.apitask.services;

import java.util.List;

import com.cherkaoui.yassine.apitask.dto.security.TokenResponseDTO;

public interface TokenJwtService {

	TokenResponseDTO createAccessToken(String username, List<String> roles);

	boolean validateToken(String token);

	String getUsername(String token);

}
