package com.jmdev.crazytrip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jmdev.crazytrip.model.LoginModel;
import com.jmdev.crazytrip.model.User;
import com.jmdev.crazytrip.service.IAuthService;

@RestController
public class AuthController {

	@Autowired
	private IAuthService ad;

	@PostMapping("/auth")
	public User getAuth(@RequestBody LoginModel login) {
		return this.ad.getAuth(login);
	}
}
