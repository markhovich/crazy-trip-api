package com.jmdev.crazytrip.model;

import lombok.Data;

public @Data class LoginModel {

	private String name;
	private String password;
	
	public LoginModel(){}
	
	public LoginModel(String name, String password){
		this.name = name;
		this.password = password;
	}
}
