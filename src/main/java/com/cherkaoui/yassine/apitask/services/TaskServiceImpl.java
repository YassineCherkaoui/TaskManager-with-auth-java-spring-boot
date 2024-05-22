package com.cherkaoui.yassine.apitask.services;

import java.util.List;
import java.util.UUID;

import com.cherkaoui.yassine.apitask.exceptions.ApiTaskResourceNotFoundException;
import com.cherkaoui.yassine.apitask.repositories.TaskRepository;
import com.cherkaoui.yassine.apitask.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.cherkaoui.yassine.apitask.dto.task.TaskMapperDTO;
import com.cherkaoui.yassine.apitask.dto.task.TaskRequestDTO;
import com.cherkaoui.yassine.apitask.dto.task.TaskResponseDTO;
import com.cherkaoui.yassine.apitask.entities.TaskEntity;
import com.cherkaoui.yassine.apitask.entities.UserEntity;

import jakarta.transaction.Transactional;

@Service
public class TaskServiceImpl implements TaskService {

	private final TaskRepository taskRepository;
	private final UserRepository userRepository;

	public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository) {
		this.taskRepository = taskRepository;
		this.userRepository = userRepository;
	}
	
	private UserEntity getUser() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		return userRepository
				.findByUsername(username)
				.orElseThrow(() -> new ApiTaskResourceNotFoundException("User not found"));
	}

	@Override
	public TaskResponseDTO findById(UUID id) {
		TaskEntity task = taskRepository
				.findByIdAndUser(id, this.getUser())
				.orElseThrow(() -> new ApiTaskResourceNotFoundException("Task not found"));
		
		return TaskMapperDTO.fromEntity(task);
	}

	@Override
	public List<TaskResponseDTO> findAll() {
		List<TaskEntity> tasks = taskRepository
				.findByUser(this.getUser());
		
		return tasks.stream().map(TaskMapperDTO::fromEntity).toList();
	}

	@Override
	public List<TaskResponseDTO> findByTitle(String title) {
		List<TaskEntity> tasks = taskRepository
				.findByTitleAndUser(title, this.getUser());
		
		return tasks.stream().map(TaskMapperDTO::fromEntity).toList();
	}

	@Override
	public List<TaskResponseDTO> findByCompleted(boolean completed) {
		List<TaskEntity> tasks = taskRepository
				.findByCompletedAndUser(completed, this.getUser());
		
		return tasks.stream().map(TaskMapperDTO::fromEntity).toList();
	}

	@Override
	@Transactional
	public TaskResponseDTO create(TaskRequestDTO dto) {
		TaskEntity task = TaskMapperDTO.fromDTO(dto);
		
		task.setUser(this.getUser());
		
		return TaskMapperDTO.fromEntity(taskRepository.save(task));
	}

	@Override
	@Transactional
	public TaskResponseDTO update(UUID id, TaskRequestDTO dto) {
		TaskEntity task = taskRepository
				.findByIdAndUser(id, this.getUser())
				.orElseThrow(() -> new ApiTaskResourceNotFoundException("Task not found"));

		task.setTitle(dto.getTitle());
		task.setDescription(dto.getDescription());
		task.setCompleted(dto.getCompleted());

		return TaskMapperDTO.fromEntity(taskRepository.save(task));
	}

	@Override
	@Transactional
	public void delete(UUID id) {
		TaskEntity task = taskRepository
				.findByIdAndUser(id, this.getUser())
				.orElseThrow(() -> new ApiTaskResourceNotFoundException("Task not found"));

		taskRepository.delete(task);
	}

}
