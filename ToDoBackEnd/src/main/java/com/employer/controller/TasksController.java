package com.employer.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.employer.dao.TasksDAO;
import com.employer.model.Tasks;

@RestController
public class TasksController {
	
	@Autowired
	Tasks tasks;
	
	@Autowired
	TasksDAO tasksDAO;
	
	@RequestMapping(value="/tasks",method=RequestMethod.GET)
	public ResponseEntity<List<Tasks>> listAllTasks(){
		List<Tasks> tasks=tasksDAO.list();
		if(tasks.isEmpty()){
			return new ResponseEntity<List<Tasks>>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<Tasks>>(tasks,HttpStatus.OK);
	}

	@RequestMapping(value="/tasks/",method=RequestMethod.POST)
	public ResponseEntity<Tasks> createTask(@RequestBody Tasks tasks,HttpSession session){
		Integer loogedInUserID=(Integer) session.getAttribute("loggedInUserID");
		tasks.setUser_id(loogedInUserID);
		tasks.setStatus("pending");
		if(tasksDAO.save(tasks)==true){
			tasks.setErrorCode("200");
			tasks.setErrorMessage("task posted");
		}
		
		
		return new ResponseEntity<Tasks>(tasks,HttpStatus.OK);
			}
	
	@RequestMapping(value="/tasksUpdate/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Tasks> updateTask(@PathVariable("id") int tasks_id,@RequestBody Tasks tasks,HttpSession session){
		
		Integer loogedInUserID=(Integer) session.getAttribute("loggedInUserID");
		tasks.setUser_id(loogedInUserID);
		if(tasksDAO.update(tasks)==true){
			tasks.setErrorCode("200");
			tasks.setErrorMessage("task posted");
		}
		
		
		return new ResponseEntity<Tasks>(tasks,HttpStatus.OK);
			}

	@RequestMapping(value="/tasksDelete/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Tasks> deleteTask(@PathVariable("id") int tasks_id){
		
		//String x=Integer.toString(user_id);
		Tasks tasks=tasksDAO.get(tasks_id);
		
		if(tasks==null){
			
			tasks=new Tasks();
			tasks.setErrorMessage("tasks does not exists with id:" + tasks_id);
			return new ResponseEntity<Tasks> (tasks,HttpStatus.NOT_FOUND);	
		}else
		tasksDAO.delete(tasks);
		
		return new ResponseEntity<Tasks> (tasks,HttpStatus.OK);		
	}
	
	@RequestMapping(value="/tasks/{id}",method=RequestMethod.GET)
	public ResponseEntity<Tasks> getTask(@PathVariable("id") int tasks_id){
		
		Tasks tasks=tasksDAO.get(tasks_id);
		if(tasks==null){
			
			tasks=new Tasks();
			//tasks.setErrorMessage("tasks does not exists with id:" + user_id);
			return new ResponseEntity<Tasks> (tasks,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Tasks> (tasks,HttpStatus.OK);
	}

	@RequestMapping(value="/tasksByUser/{uid}",method=RequestMethod.GET)
	public ResponseEntity<List<Tasks>> listTasksByUserInfo(@PathVariable("uid") int user_id){
		@SuppressWarnings("unchecked")
		List<Tasks> tasks=(List<Tasks>) tasksDAO.getByUserInfo(user_id);
		if(tasks.isEmpty()){
			return new ResponseEntity<List<Tasks>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Tasks>>(tasks,HttpStatus.OK);
	}

	
}
