package com.udacity.jwdnd.course1.cloudstorage.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Users {

	private final Integer userId;
	private final String userName ;
	private final String  salt;
	private final String  password; 
	private final String  firstName;
	private final String  lastName;
	
}
