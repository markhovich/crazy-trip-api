package com.jmdev.crazytrip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jmdev.crazytrip.model.LoginModel;
import com.jmdev.crazytrip.model.User;

@Service
public class IAuthService {

	@Autowired
	private UserService us;
	
	public User getAuth(LoginModel login) {
		User user = this.us.findByName(login.getName());
		if(user.getPassword().equals(login.getPassword())) {
			return user;
		}

		return null;
	}
}
