package com.jmdev.crazytrip.exceptions;

public class UserNameException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UserNameException(String name) {
		super(String.format("Le pseudo " + name + " est déjà pris"));
	}

}
