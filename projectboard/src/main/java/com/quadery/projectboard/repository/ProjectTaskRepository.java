package com.quadery.projectboard.repository;

import org.springframework.data.repository.CrudRepository;

import com.quadery.projectboard.domain.ProjectTask;

public interface ProjectTaskRepository extends CrudRepository<ProjectTask, Long> {

	ProjectTask getById(Long id);
}
