package com.cherkaoui.yassine.apitask.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cherkaoui.yassine.apitask.dto.user.UserResponseDTO;
import com.cherkaoui.yassine.apitask.dto.utils.LinkUtilDTO;
import com.cherkaoui.yassine.apitask.exceptions.ApiTaskMessageError;
import com.cherkaoui.yassine.apitask.services.UserService;
import com.cherkaoui.yassine.apitask.services.UserServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path = "/api/users")
@Tag(name = "Users", description = "Endpoints for users")
public class UserController {

	private final UserService userService;

	public UserController(UserServiceImpl userService) {
		this.userService = userService;
	}

	@GetMapping(path = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(
			security = @SecurityRequirement(name = "bearerAuth"),
			summary = "Get user data",
			description = "Get user data",
			tags = {"Users"},
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "Get user data",
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
							responseCode = "401",
							description = "Unauthorized",
							content = @Content(
									mediaType = MediaType.APPLICATION_JSON_VALUE,
									schema = @Schema(implementation = ApiTaskMessageError.class)
							)
					)
			}
	)
	public ResponseEntity<UserResponseDTO> me() {
		UserResponseDTO response = userService.me();

		response.addLink(new LinkUtilDTO("self", "/api/users/me"));
		response.addLink(new LinkUtilDTO("login", "/api/auth/login"));

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(
			security = @SecurityRequirement(name = "bearerAuth"),
			summary = "Show all users",
			description = "Show all users",
			tags = {"Users"},
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "Show all users",
							content = @Content(
									mediaType = MediaType.APPLICATION_JSON_VALUE,
									array = @ArraySchema(schema = @Schema(implementation = UserResponseDTO.class))
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
							responseCode = "401",
							description = "Unauthorized",
							content = @Content(
									mediaType = MediaType.APPLICATION_JSON_VALUE,
									schema = @Schema(implementation = ApiTaskMessageError.class)
							)
					),
					@ApiResponse(
							responseCode = "403",
							description = "Forbidden",
							content = @Content(
									mediaType = MediaType.APPLICATION_JSON_VALUE,
									schema = @Schema(implementation = ApiTaskMessageError.class)
							)
					)
			}
	)
	public ResponseEntity<List<UserResponseDTO>> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
	}

}
