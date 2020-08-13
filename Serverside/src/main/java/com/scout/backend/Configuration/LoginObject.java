package com.scout.backend.Configuration;

public class LoginObject {
	
	String firstName;
	String lastName;
	String userId;
	boolean success;
	
	
	public LoginObject(String firstName, String lastName, String userId, boolean success) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userId = userId;
		this.success = success;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public boolean isSuccess() {
		return success;
	}


	public void setSuccess(boolean success) {
		this.success = success;
	}

}
