package com.github.julioevencio.apitask.services;

import java.util.List;
import java.util.UUID;

import com.github.julioevencio.apitask.dto.task.TaskRequestDTO;
import com.github.julioevencio.apitask.dto.task.TaskResponseDTO;

public interface TaskService {

	TaskResponseDTO findById(UUID id);

	List<TaskResponseDTO> findAll();

	List<TaskResponseDTO> findByTitle(String title);

	List<TaskResponseDTO> findByCompleted(boolean completed);

	TaskResponseDTO create(TaskRequestDTO dto);

	TaskResponseDTO update(UUID id, TaskRequestDTO dto);

	void delete(UUID id);

}
