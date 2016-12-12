package com.employer.dao;

import java.util.List;

import com.employer.model.Tasks;

public interface TasksDAO {
	
    public boolean save(Tasks tasks); 
	
	public boolean update(Tasks tasks);
	
	public boolean delete(Tasks tasks);
	
	public Tasks get(int task_id);
	
	public Tasks getByUserInfo(int user_id);
	
	public List<Tasks> list();


}
