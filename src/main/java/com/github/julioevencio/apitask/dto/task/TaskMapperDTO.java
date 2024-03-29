package com.github.julioevencio.apitask.dto.task;

import com.github.julioevencio.apitask.entities.TaskEntity;

public class TaskMapperDTO {

	public static TaskEntity fromDTO(TaskRequestDTO dto) {
		return new TaskEntity(null, dto.getTitle(), dto.getDescription(), dto.getCompleted(), null);
	}

	public static TaskResponseDTO fromEntity(TaskEntity entity) {
		return new TaskResponseDTO(entity.getId(), entity.getTitle(), entity.getDescription(), entity.getCompleted());
	}

}
