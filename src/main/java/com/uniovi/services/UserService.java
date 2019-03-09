package com.uniovi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.uniovi.entities.User;
import com.uniovi.entities.type.Rol;
import com.uniovi.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository usersRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public List<User> getUsers() {
		return usersRepository.findAll();
	}
	
	public List<User> getUsersStandardActive(){
		return usersRepository.findByActiveAndRol(true,Rol.ROLE_USER );
	}

	public User getUser(Long id) {
		return usersRepository.findById(id).get();
	}

	public User getUserByEmail(String email) {
		return usersRepository.findByEmail(email);
	}

	public void addUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

		usersRepository.save(user);
	}

	public void deleteUser(Long id) {
		User user = usersRepository.getOne(id);
		user.setActive(false);
		usersRepository.save(user);
	}
}
