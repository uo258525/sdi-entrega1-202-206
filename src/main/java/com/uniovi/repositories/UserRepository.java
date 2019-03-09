package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uniovi.entities.User;
import com.uniovi.entities.type.Rol;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);
	
	List<User> findByActiveAndRol(boolean active, Rol rol);

}
