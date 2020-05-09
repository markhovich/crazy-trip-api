package com.jmdev.crazytrip.exceptions;

public class UserEmailException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UserEmailException(String email) {
		super(String.format("L'email " + email + " est déjà pris"));
	}
}
