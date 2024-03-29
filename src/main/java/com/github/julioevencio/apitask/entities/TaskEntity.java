package com.github.julioevencio.apitask.entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_task")
public class TaskEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(nullable = false, length = 100)
	private String title;

	@Column(nullable = false, length = 1000)
	private String description;

	@Column(nullable = false)
	private Boolean completed;

	@ManyToOne
	private UserEntity user;

	public TaskEntity() {
	}

	public TaskEntity(UUID id, String title, String description, Boolean completed, UserEntity user) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.completed = completed;
		this.user = user;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(completed, description, id, title, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaskEntity other = (TaskEntity) obj;
		return Objects.equals(completed, other.completed) && Objects.equals(description, other.description)
				&& Objects.equals(id, other.id) && Objects.equals(title, other.title)
				&& Objects.equals(user, other.user);
	}

}
