package com.github.julioevencio.apitask.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.julioevencio.apitask.entities.TaskEntity;
import com.github.julioevencio.apitask.entities.UserEntity;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

	Optional<TaskEntity> findByIdAndUser(UUID id, UserEntity user);

	List<TaskEntity> findByUser(UserEntity user);

	List<TaskEntity> findByTitleAndUser(String title, UserEntity user);

	List<TaskEntity> findByCompletedAndUser(boolean completed, UserEntity user);

}
