package com.github.julioevencio.apitask.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.julioevencio.apitask.dto.task.TaskRequestDTO;
import com.github.julioevencio.apitask.dto.task.TaskResponseDTO;
import com.github.julioevencio.apitask.dto.utils.LinkUtilDTO;
import com.github.julioevencio.apitask.exceptions.ApiTaskMessageError;
import com.github.julioevencio.apitask.services.TaskService;
import com.github.julioevencio.apitask.services.TaskServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/tasks")
@Tag(name = "Task", description = "Endpoints for tasks")
public class TaskController {

	private final TaskService taskService;

	public TaskController(TaskServiceImpl taskServiceImpl) {
		this.taskService = taskServiceImpl;
	}
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(
			security = @SecurityRequirement(name = "bearerAuth"),
			summary = "Find a task for id",
			description = "Find a task for id",
			tags = {"Task"},
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "Find a task for id",
							content = @Content(
									mediaType = MediaType.APPLICATION_JSON_VALUE,
									schema = @Schema(implementation = TaskResponseDTO.class)
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
	public ResponseEntity<TaskResponseDTO> findById(@PathVariable UUID id) {
		TaskResponseDTO response = taskService.findById(id);

		response.addLink(new LinkUtilDTO("self", "/api/tasks/{id}"));
		response.addLink(new LinkUtilDTO("find all", "/api/tasks"));
		response.addLink(new LinkUtilDTO("find by title", "/api/tasks/title/{title}"));
		response.addLink(new LinkUtilDTO("find by completed", "/api/tasks/completed/{completed}"));
		response.addLink(new LinkUtilDTO("create", "/api/tasks"));
		response.addLink(new LinkUtilDTO("update", "/api/tasks/{id}"));
		response.addLink(new LinkUtilDTO("delete", "/api/tasks/{id}"));
		response.addLink(new LinkUtilDTO("me", "/api/users/me"));

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(
			security = @SecurityRequirement(name = "bearerAuth"),
			summary = "Show all task of a user",
			description = "Show all task of a user",
			tags = {"Task"},
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "Show all task of a user",
							content = @Content(
									mediaType = MediaType.APPLICATION_JSON_VALUE,
									array = @ArraySchema(schema = @Schema(implementation = TaskResponseDTO.class))
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
	public ResponseEntity<List<TaskResponseDTO>> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(taskService.findAll());
	}

	@GetMapping(path = "/title/{title}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(
			security = @SecurityRequirement(name = "bearerAuth"),
			summary = "Show all task of a user by title",
			description = "Show all task of a user by title",
			tags = {"Task"},
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "Show all task of a user by title",
							content = @Content(
									mediaType = MediaType.APPLICATION_JSON_VALUE,
									array = @ArraySchema(schema = @Schema(implementation = TaskResponseDTO.class))
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
	public ResponseEntity<List<TaskResponseDTO>> findByTitle(@PathVariable String title) {
		return ResponseEntity.status(HttpStatus.OK).body(taskService.findByTitle(title));
	}

	@GetMapping(path = "/completed/{completed}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(
			security = @SecurityRequirement(name = "bearerAuth"),
			summary = "Show all task of a user by completed",
			description = "Show all task of a user by completed",
			tags = {"Task"},
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "Show all task of a user by completed",
							content = @Content(
									mediaType = MediaType.APPLICATION_JSON_VALUE,
									array = @ArraySchema(schema = @Schema(implementation = TaskResponseDTO.class))
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
	public ResponseEntity<List<TaskResponseDTO>> findByCompleted(@PathVariable Boolean completed) {
		return ResponseEntity.status(HttpStatus.OK).body(taskService.findByCompleted(completed));
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(
			security = @SecurityRequirement(name = "bearerAuth"),
			summary = "Create a new task for a user",
			description = "Create a new task for a user",
			tags = {"Task"},
			responses = {
					@ApiResponse(
							responseCode = "201",
							description = "Task Created",
							content = @Content(
									mediaType = MediaType.APPLICATION_JSON_VALUE,
									schema = @Schema(implementation = TaskResponseDTO.class)
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
	public ResponseEntity<TaskResponseDTO> create(@RequestBody @Valid TaskRequestDTO dto) {
		TaskResponseDTO response = taskService.create(dto);

		response.addLink(new LinkUtilDTO("self", "/api/tasks"));
		response.addLink(new LinkUtilDTO("find by id", "/api/tasks/{id}"));
		response.addLink(new LinkUtilDTO("find all", "/api/tasks"));
		response.addLink(new LinkUtilDTO("find by title", "/api/tasks/title/{title}"));
		response.addLink(new LinkUtilDTO("find by completed", "/api/tasks/completed/{completed}"));
		response.addLink(new LinkUtilDTO("update", "/api/tasks/{id}"));
		response.addLink(new LinkUtilDTO("delete", "/api/tasks/{id}"));
		response.addLink(new LinkUtilDTO("me", "/api/users/me"));

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(
			security = @SecurityRequirement(name = "bearerAuth"),
			summary = "Update a task for a user",
			description = "Update a task for a user",
			tags = {"Task"},
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "Task Updated",
							content = @Content(
									mediaType = MediaType.APPLICATION_JSON_VALUE,
									schema = @Schema(implementation = TaskResponseDTO.class)
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
	public ResponseEntity<TaskResponseDTO> update(@PathVariable UUID id, @RequestBody @Valid TaskRequestDTO dto) {
		TaskResponseDTO response = taskService.update(id, dto);

		response.addLink(new LinkUtilDTO("self", "/api/tasks/{id}"));
		response.addLink(new LinkUtilDTO("find by id", "/api/tasks/{id}"));
		response.addLink(new LinkUtilDTO("find all", "/api/tasks"));
		response.addLink(new LinkUtilDTO("find by title", "/api/tasks/title/{title}"));
		response.addLink(new LinkUtilDTO("find by completed", "/api/tasks/completed/{completed}"));
		response.addLink(new LinkUtilDTO("create", "/api/tasks"));
		response.addLink(new LinkUtilDTO("delete", "/api/tasks/{id}"));
		response.addLink(new LinkUtilDTO("me", "/api/users/me"));

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(
			security = @SecurityRequirement(name = "bearerAuth"),
			summary = "Delete a task for a user",
			description = "Delete a task for a user",
			tags = {"Task"},
			responses = {
					@ApiResponse(
							responseCode = "204",
							description = "Task Deleted",
							content = @Content(
									mediaType = MediaType.APPLICATION_JSON_VALUE
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
	public ResponseEntity<Void> delete(@PathVariable UUID id) {
		taskService.delete(id);

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
