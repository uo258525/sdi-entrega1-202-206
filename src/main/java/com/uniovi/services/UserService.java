package com.uniovi.services;

import java.util.*;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.uniovi.entities.User;
import com.uniovi.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository usersRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		usersRepository.findAll().forEach(users::add);
		return users;
	}

	public User getUser(Long email) {
		return usersRepository.findById(email).get();
	}
 
	public User getUserByEmail(String email) {
		return usersRepository.findByEmail(email);
	}

	public void addUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

		usersRepository.save(user);
	}

	public void deleteUser(Long id) {
		usersRepository.deleteById(id);
	}
}
