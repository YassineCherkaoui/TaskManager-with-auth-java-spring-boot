package com.github.julioevencio.apitask.dto.task;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TaskRequestDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Invalid title")
	@Size(max = 100, min = 1, message = "The title must be between 1 and 100 characters long")
	private final String title;

	@NotBlank(message = "Invalid description")
	@Size(max = 1000, min = 1, message = "The description must be between 1 and 1000 characters long")
	private final String description;

	@NotNull(message = "Invalid completed")
	private final Boolean completed;

	public TaskRequestDTO(String title, String description, Boolean completed) {
		this.title = title;
		this.description = description;
		this.completed = completed;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Boolean getCompleted() {
		return completed;
	}

}
