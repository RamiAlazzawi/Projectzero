package com.revature;

import com.revature.bean.UserDao;

public class D_User extends UserDao  {
	private static int id;
	private String frist;
	private String last;
	private String username;
	private String password;
	private String type;
	
	
	public D_User() {
		
	}
	
	public void setId(int id) {
		 this.id=id;
	}
	
	public static int getId() {
		return id;
	}
	
	public void setUserName(String username) {
		 this.username=username;
	}
	
	public String getUserName() {
		return username;
	}
	
	public void setFirstName(String first) {
		this.frist=first;
	}
	
	public String getFirstName() {
		return frist;
	}
	
	public void setLastName(String last) {
		this.last=last;
	}
	
	public String getLastName() {
		return last;
	}
	
	public void setPassWord(String pass) {
		this.password=pass;
	}
	
	public String getPassWord() {
		return password;
	}
	
	public void setUserType(String type) {
		this.type=type;
	}
	
	public String getUserType() {
		return type;
	}
	
	
	
		
 }
	
	
	


	

