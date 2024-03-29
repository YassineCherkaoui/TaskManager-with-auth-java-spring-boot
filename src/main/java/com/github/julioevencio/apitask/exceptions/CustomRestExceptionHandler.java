package com.github.julioevencio.apitask.exceptions;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomRestExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiTaskMessageError> handlerException(Exception e) {
		ApiTaskMessageError errors = new ApiTaskMessageError("Bad Request", Arrays.asList("Bad request..."));

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ApiTaskMessageError> handlerRuntimeException(RuntimeException e) {
		ApiTaskMessageError errors = new ApiTaskMessageError("Bad Request", Arrays.asList("Bad request..."));

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiTaskMessageError> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		List<String> errors = e.getBindingResult().getAllErrors().stream().map(error -> error.getDefaultMessage())
				.collect(Collectors.toList());

		ApiTaskMessageError error = new ApiTaskMessageError("Invalid data", errors);

		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
	}

	@ExceptionHandler(ApiTaskLoginException.class)
	public ResponseEntity<ApiTaskMessageError> handlerApiTaskLoginException(ApiTaskLoginException e) {
		ApiTaskMessageError errors = new ApiTaskMessageError("Login error", Arrays.asList(e.getMessage()));

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errors);
	}

	@ExceptionHandler(ApiTaskResourceNotFoundException.class)
	public ResponseEntity<ApiTaskMessageError> handlerApiTaskResourceNotFoundException(ApiTaskResourceNotFoundException e) {
		ApiTaskMessageError errors = new ApiTaskMessageError("Resource not found", Arrays.asList(e.getMessage()));

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
	}

	@ExceptionHandler(ApiTaskSQLException.class)
	public ResponseEntity<ApiTaskMessageError> handlerApiTaskSQLException(ApiTaskSQLException e) {
		ApiTaskMessageError errors = new ApiTaskMessageError("Invalid data", Arrays.asList(e.getMessage()));

		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errors);
	}

}
