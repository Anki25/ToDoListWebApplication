package com.employer.dao;

import java.util.List;

import com.employer.model.UserInfo;

public interface UserInfoDAO {

public boolean save(UserInfo userInfo); 
	
	public boolean update(UserInfo userInfo);
	
	public boolean delete(UserInfo userInfo);
	
	public UserInfo get(int userID);
	
	public UserInfo getName(String name);
	
	public List<UserInfo> list();
	
	public UserInfo isValidUser(String email, String password);
	
	
}
