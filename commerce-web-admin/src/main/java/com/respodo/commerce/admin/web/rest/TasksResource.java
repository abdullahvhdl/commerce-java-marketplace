package com.respodo.commerce.admin.web.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.FormService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.FormData;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.impl.form.EnumFormType;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.respodo.commerce.repository.config.SecurityUtils;
import com.respodo.commerce.service.QuickOrderService;

@RestController
@RequestMapping("/api")
public class TasksResource {

	@Autowired
	private TaskService taskService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private IdentityService identityService;
	@Autowired
	private QuickOrderService quickOrderService;
	@Autowired
	private FormService formService;

	private final Logger log = LoggerFactory.getLogger(TasksResource.class);

	@RequestMapping(value="/task/{taskId}",method=RequestMethod.POST)
	public ResponseEntity<?> getTask(@PathVariable String taskId){
		TaskQuery query = taskService.createTaskQuery();
		query.taskId(taskId);
		Task task=query.singleResult();
		if(task ==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		TaskResponse taskResponse=new TaskResponse(task);
		TaskFormData formData=formService.getTaskFormData(taskId);
		taskResponse.setFormProperties(getFormProperties(formData));
		return new ResponseEntity<TaskResponse>(taskResponse,HttpStatus.OK) ;
	}

	private List<FormPropertyResponse> getFormProperties(TaskFormData formData) {
		List<FormPropertyResponse> formProperties= new ArrayList<FormPropertyResponse>();
		for(FormProperty property:formData.getFormProperties()){
			FormPropertyResponse response=new FormPropertyResponse(property);
			if(property.getType().getName().equals("enum")){
				EnumFormType type=(EnumFormType) property.getType();
				Map <String,String> values=(Map) type.getInformation("values");
				response.setEnumValues(values);
			}
			formProperties.add(response);
		}
		
		return formProperties;
	}

	@RequestMapping("/tasks")
	public List<TaskResponse> getTasks() {
		TaskQuery query = taskService.createTaskQuery();
		String userId=SecurityUtils.getCurrentLogin();
		query.taskAssignee(userId).orderByTaskPriority().desc()
				.orderByDueDateNullsLast().desc();
		List<Task> tasks = query.list();
		List<TaskResponse> tasksResponse = new ArrayList<TaskResponse>();
		tasks.forEach(task -> {
			TaskResponse response = new TaskResponse(task);
			tasksResponse.add(response);
		});
		return tasksResponse;
	}

	@RequestMapping("/unassigned")
	public List<TaskResponse> getUnassignedTasks() {
		TaskQuery query = taskService.createTaskQuery();
		String userId=SecurityUtils.getCurrentLogin();
		query.taskUnassigned().taskCandidateUser(userId)
				.orderByTaskPriority().desc().orderByDueDateNullsLast().desc();
		List<Task> tasks = query.list();
		List<TaskResponse> tasksResponse = new ArrayList<TaskResponse>();
		tasks.forEach(task -> {
			TaskResponse response = new TaskResponse(task);
			tasksResponse.add(response);
		});
		return tasksResponse;
	}

	@RequestMapping(value = "/claim/{taskId}", method = RequestMethod.POST)
	public void claim(@PathVariable String taskId) {
		String userId=SecurityUtils.getCurrentLogin();
		taskService.claim(taskId, userId);
	}
	
	@RequestMapping(value="submit",method=RequestMethod.POST)
	public void submit(@RequestBody TaskResponse task){
		Map<String,String> properties=new HashMap<String, String>();
		for(FormPropertyResponse property:task.getFormProperties()){
			properties.put(property.getId(), property.getValue());
		}
		formService.submitTaskFormData(task.getId(), properties);
	}


}
