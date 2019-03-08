package com.uniovi.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.User;
import com.uniovi.entities.type.Rol;

@Service

public class InsertData {
	@Autowired
	private UserService userService;
	
	@PostConstruct 
	public void insertData() {
		 User user = new User();
		 user.setRol(Rol.ROLE_ADMIN);
		 user.setEmail("admin");
		 user.setPassword("admin");
		 user.setPassword2("admin");
		 userService.addUser(user);
		 
	}

}
