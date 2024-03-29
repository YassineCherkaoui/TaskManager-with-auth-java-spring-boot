package com.github.julioevencio.apitask.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.julioevencio.apitask.dto.security.LoginRequestDTO;
import com.github.julioevencio.apitask.dto.security.TokenResponseDTO;
import com.github.julioevencio.apitask.dto.user.UserRequestDTO;
import com.github.julioevencio.apitask.dto.user.UserResponseDTO;
import com.github.julioevencio.apitask.dto.utils.LinkUtilDTO;
import com.github.julioevencio.apitask.exceptions.ApiTaskMessageError;
import com.github.julioevencio.apitask.services.UserService;
import com.github.julioevencio.apitask.services.UserServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/auth")
@Tag(name = "Auth", description = "Endpoints for authentication")
public class AuthController {

	private final UserService userService;

	public AuthController(UserServiceImpl userService) {
		this.userService = userService;
	}

	@PostMapping(path = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(
			summary = "Register a user",
			description = "Register a user",
			tags = {"Auth"},
			responses = {
					@ApiResponse(
							responseCode = "201",
							description = "User created",
							content = @Content(
									mediaType = MediaType.APPLICATION_JSON_VALUE,
									schema = @Schema(implementation = UserResponseDTO.class)
							)
					),
					@ApiResponse(
							responseCode = "400",
							description = "Bad request",
							content = @Content(
									mediaType = MediaType.APPLICATION_JSON_VALUE,
									schema = @Schema(implementation = ApiTaskMessageError.class)
							)
					),
					@ApiResponse(
							responseCode = "422",
							description = "Unprocessable entity",
							content = @Content(
									mediaType = MediaType.APPLICATION_JSON_VALUE,
									schema = @Schema(implementation = ApiTaskMessageError.class)
							)
					)
			}
	)
	public ResponseEntity<UserResponseDTO> register(@RequestBody @Valid UserRequestDTO dto) {
		UserResponseDTO response = userService.register(dto);

		response.addLink(new LinkUtilDTO("self", "/api/auth/register"));
		response.addLink(new LinkUtilDTO("login", "/api/auth/login"));

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(
			summary = "Generate jwt token",
			description = "Generate jwt token",
			tags = {"Auth"},
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "Generate jwt token",
							content = @Content(
									mediaType = MediaType.APPLICATION_JSON_VALUE,
									schema = @Schema(implementation = UserResponseDTO.class)
							)
					),
					@ApiResponse(
							responseCode = "400",
							description = "Bad request",
							content = @Content(
									mediaType = MediaType.APPLICATION_JSON_VALUE,
									schema = @Schema(implementation = ApiTaskMessageError.class)
							)
					),
					@ApiResponse(
							responseCode = "422",
							description = "Unprocessable entity",
							content = @Content(
									mediaType = MediaType.APPLICATION_JSON_VALUE,
									schema = @Schema(implementation = ApiTaskMessageError.class)
							)
					),
					@ApiResponse(
							responseCode = "401",
							description = "Unauthorized",
							content = @Content(
									mediaType = MediaType.APPLICATION_JSON_VALUE,
									schema = @Schema(implementation = ApiTaskMessageError.class)
							)
					)
			}
	)
	public ResponseEntity<TokenResponseDTO> login(@RequestBody @Valid LoginRequestDTO dto) {
		TokenResponseDTO response = userService.login(dto);

		response.addLink(new LinkUtilDTO("self", "/api/auth/login"));
		response.addLink(new LinkUtilDTO("register", "/api/auth/register"));
		response.addLink(new LinkUtilDTO("me", "/api/users/me"));

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
