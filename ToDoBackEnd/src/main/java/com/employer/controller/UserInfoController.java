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

import com.employer.dao.UserInfoDAO;
import com.employer.model.UserInfo;

@RestController
public class UserInfoController {

	@Autowired
	UserInfoDAO userInfoDAO;	
	
	@RequestMapping(value="/users",method=RequestMethod.GET)
	public ResponseEntity<List<UserInfo>> listAllUsers(){
		List<UserInfo> userInfo=userInfoDAO.list();
		if(userInfo.isEmpty()){
			return new ResponseEntity<List<UserInfo>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<UserInfo>>(userInfo,HttpStatus.OK);
	}

	@RequestMapping(value="/userInfo/",method=RequestMethod.POST)
	public ResponseEntity<UserInfo> createUser(@RequestBody UserInfo userInfo){
		
		if(userInfoDAO.get(userInfo.getUser_id())==null){
			userInfoDAO.save(userInfo);			
		}
		
		userInfo.setErrorMessage("userInfo saved" + userInfo.getUser_id());
		return new ResponseEntity<UserInfo>(userInfo,HttpStatus.OK);
			}
	
	@RequestMapping(value="/userInfo/{id}",method=RequestMethod.PUT)
	public ResponseEntity<UserInfo> updateUser(@PathVariable("id") int user_id,@RequestBody UserInfo userInfo){
		
		if(userInfoDAO.get(user_id)==null){
					
			userInfo=new UserInfo();
			userInfo.setErrorMessage("userInfo does not exists with id:" + userInfo.getUser_id());
			return new ResponseEntity<UserInfo> (userInfo,HttpStatus.NOT_FOUND);
		}
		userInfoDAO.update(userInfo);
		
		return new ResponseEntity<UserInfo> (userInfo,HttpStatus.OK);		
	}

	@RequestMapping(value="/userInfo/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<UserInfo> deleteUser(@PathVariable("id") int user_id){
		
		//String x=Integer.toString(user_id);
		UserInfo userInfo=userInfoDAO.get(user_id);
		
		if(userInfo==null){
			
			userInfo=new UserInfo();
			userInfo.setErrorMessage("userInfo does not exists with id:" + user_id);
			return new ResponseEntity<UserInfo> (userInfo,HttpStatus.NOT_FOUND);	
		}else
		userInfoDAO.delete(userInfo);
		
		return new ResponseEntity<UserInfo> (userInfo,HttpStatus.OK);		
	}
	
	@RequestMapping(value="/userInfo/{id}",method=RequestMethod.GET)
	public ResponseEntity<UserInfo> getUser(@PathVariable("id") int user_id){
		
		UserInfo userInfo=userInfoDAO.get(user_id);
		if(userInfo==null){
			
			userInfo=new UserInfo();
			//userInfo.setErrorMessage("userInfo does not exists with id:" + user_id);
			return new ResponseEntity<UserInfo> (userInfo,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<UserInfo> (userInfo,HttpStatus.OK);
	}

	@RequestMapping(value="/userInfo/authenticate/",method=RequestMethod.POST)
	public ResponseEntity<UserInfo> authenticate(@RequestBody UserInfo userInfo,HttpSession session){
			
		userInfo=userInfoDAO.isValidUser(userInfo.getEmail(),userInfo.getPassword());	
		if(userInfo==null){
			userInfo=new UserInfo();
			userInfo.setErrorCode("404");
			return new ResponseEntity<UserInfo>(userInfo,HttpStatus.UNAUTHORIZED);
				}
		else{
			userInfo.setErrorMessage("200");
			session.setAttribute("loggedInUser",userInfo);
			session.setAttribute("loggedInUserID",userInfo.getUser_id());
			
			}
		return new ResponseEntity<UserInfo>(userInfo,HttpStatus.OK);
	}

	@RequestMapping(value="/userInfo/logout",method=RequestMethod.GET)
	public ResponseEntity<UserInfo> logOut(HttpSession session){
		
		//Integer loogedInUserID=(Integer) session.getAttribute("loggedInUserID");
		UserInfo userInfo=new UserInfo();
		session.invalidate();
		userInfo.setErrorCode("200");
		userInfo.setErrorMessage("successfully logged out");
		return new ResponseEntity<UserInfo>(userInfo,HttpStatus.OK);
		
	}

}
