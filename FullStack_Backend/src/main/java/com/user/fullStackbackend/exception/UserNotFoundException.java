package com.user.fullStackbackend.exception;

public class UserNotFoundException extends RuntimeException{
	
	 public UserNotFoundException(int id){
	        super("Could not found the user with id "+ id);
	    }

}
