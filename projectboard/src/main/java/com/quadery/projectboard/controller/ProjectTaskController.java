package com.quadery.projectboard.controller;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.quadery.projectboard.domain.ProjectTask;
import com.quadery.projectboard.service.ProjectTaskService;

@Controller
@CrossOrigin
@RequestMapping(value = "/api/board")
public class ProjectTaskController {

	@Autowired
	private ProjectTaskService projectTaskService;
	
	@PostMapping("")
	public ResponseEntity<?> addTask(@Valid @RequestBody ProjectTask task, BindingResult result) {
		
		if(result.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			errorMap = result.getFieldErrors().stream()
					.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
			
			return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
		}
		ProjectTask newTask = projectTaskService.saveOrUpdateTask(task);
		
		return new ResponseEntity<ProjectTask>(newTask, HttpStatus.CREATED);
	}
	
	@GetMapping("/all") 
	public ResponseEntity<?> getAllTasks() {
		Iterable<ProjectTask> allTasks = projectTaskService.findAll();
		return new ResponseEntity<Iterable<ProjectTask>>(allTasks, HttpStatus.OK);
	}
	
	@GetMapping("/{taskId}")
	public ResponseEntity<?> getById(@PathVariable Long taskId) {
		ProjectTask projectTask = projectTaskService.findById(taskId);
        return new ResponseEntity<ProjectTask>(projectTask, HttpStatus.OK);
	}
	
	@DeleteMapping("/{taskId}")
	public ResponseEntity<?> deleteProjectTask(@PathVariable Long taskId){
        projectTaskService.delete(taskId);
        return new ResponseEntity<String>("Project Task deleted", HttpStatus.OK);
    }
}
