package com.employer.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

@Entity
@Table(name="USERS_INFO")
@Component
public class UserInfo extends BaseDomain{
	
	@Id
	@GeneratedValue(generator="InvSeq") 
    @SequenceGenerator(name="InvSeq",sequenceName="USERS_INFO_SEQ", allocationSize=1) 
	private int user_id;

	@NotEmpty(message="Please fill the first name")
	private String user_name;
	@NotEmpty(message="Password is a must")
	private String password;
	//@NotEmpty(message="Confirm Password must be filled ")
	@Transient
	private String cpassword;	
	//@NotEmpty(message="Please enter emailID")
	private String email;
    @Size(min=10,max=10)
	private String contact;
	private String profession;
	private String city;
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCpassword() {
		return cpassword;
	}
	public void setCpassword(String cpassword) {
		this.cpassword = cpassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProfession() {
		return profession;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	
		


}
