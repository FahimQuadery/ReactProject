package com.quadery.projectboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quadery.projectboard.domain.ProjectTask;
import com.quadery.projectboard.repository.ProjectTaskRepository;


@Service
public class ProjectTaskService {

	@Autowired
	private ProjectTaskRepository projectTaskRepository;
	
	public ProjectTask saveOrUpdateTask(ProjectTask task) {
		
		if(task.getStatus()==null || "".equals(task.getStatus())) {
			task.setStatus("TO_DO");
		}
		
		return projectTaskRepository.save(task);
	}
	
	public Iterable<ProjectTask> findAll() {
		Iterable<ProjectTask> allTasks = projectTaskRepository.findAll();
		return allTasks;
	}
	
	public ProjectTask findById(Long id) {
		return projectTaskRepository.getById(id);
	}
	
	public void delete(Long id) {
		ProjectTask taskToDelete = projectTaskRepository.getById(id);
		projectTaskRepository.delete(taskToDelete);
	}
}
