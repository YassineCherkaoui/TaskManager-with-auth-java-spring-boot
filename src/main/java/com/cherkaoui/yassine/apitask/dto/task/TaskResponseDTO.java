package com.cherkaoui.yassine.apitask.dto.task;

import java.util.UUID;

import com.cherkaoui.yassine.apitask.dto.ResponseDTO;

public class TaskResponseDTO extends ResponseDTO {

	private static final long serialVersionUID = 1L;

	private final UUID id;
	private final String title;
	private final String description;
	private final Boolean completed;

	public TaskResponseDTO(UUID id, String title, String description, Boolean completed) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.completed = completed;
	}

	public UUID getId() {
		return id;
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
