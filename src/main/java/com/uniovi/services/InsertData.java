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
		 user.setEmail("admin@email.com");
		 user.setPassword("admin");
		 user.setPassword2("admin");
		 user.setActive(true);
		 userService.addUser(user);
		 user = new User();
		 user.setRol(Rol.ROLE_USER);
		 user.setEmail("user1@email.com");
		 user.setPassword("user1");
		 user.setPassword2("user1");
		 user.setName("Juan");
		 user.setSurname("Diaz");
		 user.setActive(true);
		 userService.addUser(user);
		 user = new User();
		 user.setRol(Rol.ROLE_USER);
		 user.setEmail("user2@email.com");
		 user.setPassword("user2");
		 user.setPassword2("user2");
		 user.setName("Lucia");
		 user.setSurname("Fernandez");
		 user.setActive(true);
		 userService.addUser(user);
		 
	}

}
