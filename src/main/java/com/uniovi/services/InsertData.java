package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.entities.type.Rol;

@Service
public class InsertData {
	
	@Autowired
	private UserService userService;

	@Autowired
	private OffersService offerService;

	@PostConstruct
	public void insertData() {
		// Ahora las volvemos a crear
		User user = new User();
		user.setRol(Rol.ROLE_ADMIN);
		user.setEmail("admin@email.com");
		user.setPassword("admin");
		user.setPassword2("admin");
		user.setActive(true);

		User user1 = new User();
		user1.setRol(Rol.ROLE_USER);
		user1.setEmail("user1@email.com");
		user1.setPassword("user1");
		user1.setPassword2("user1");
		user1.setName("Juan");
		user1.setSurname("Diaz");
		user1.setActive(true);

		User user2 = new User();
		user2.setRol(Rol.ROLE_USER);
		user2.setEmail("user2@email.com");
		user2.setPassword("user2");
		user2.setPassword2("user2");
		user2.setName("Lucia");
		user2.setSurname("Fernandez");
		user2.setActive(true);

		User user3 = new User();
		user3.setRol(Rol.ROLE_USER);
		user3.setEmail("user3@email.com");
		user3.setPassword("user3");
		user3.setPassword2("user3");
		user3.setName("Alba");
		user3.setSurname("García");
		user3.setActive(true);

		User user4 = new User();
		user4.setRol(Rol.ROLE_USER);
		user4.setEmail("user4@email.com");
		user4.setPassword("user4");
		user4.setPassword2("user4");
		user4.setName("Cuatroneno");
		user4.setSurname("Fouracio");
		user4.setActive(true);

		User user5 = new User();
		user5.setRol(Rol.ROLE_USER);
		user5.setEmail("user5@email.com");
		user5.setPassword("user5");
		user5.setPassword2("user5");
		user5.setName("Cincuento");
		user5.setSurname("Fivement");
		user5.setActive(true);
		
		userService.addUser(user);
		userService.addUser(user1);
		userService.addUser(user2);
		userService.addUser(user3);
		userService.addUser(user4);
		userService.addUser(user5);

		List<Offer> user1Offers = new ArrayList<Offer>();
		for (int i = 1; i < 4; i++)
			user1Offers.add(new Offer("Offer 1", "seminuevo", i * 1.0,
					user1));
	
		offerService.saveAll(user1Offers);		

		List<Offer> user2Offers = new ArrayList<Offer>();
		for (int i = 1; i < 4; i++)
			user2Offers.add(new Offer("Offer 2", "muy bueno", i * 1.0,
					user2));
		offerService.saveAll(user2Offers);
		
		List<Offer> user3Offers = new ArrayList<Offer>();
		for (int i = 1; i < 6; i++)
			user3Offers.add(new Offer("Offer 3", "seminuevo", i * 1.0,
					user3));
		offerService.saveAll(user3Offers);
		
		List<Offer> user4Offers = new ArrayList<Offer>();
		for (int i = 1; i < 5; i++)
			user4Offers.add(new Offer("Offer 4", "muy buena calidad", i * 1.0,
					user4));
		offerService.saveAll(user4Offers);

		List<Offer> user5Offers = new ArrayList<Offer>();
		for (int i = 96; i <= 100; i++)
			user5Offers.add(new Offer("Offer 5", "excelente", i * 1.0,
					user5));
		offerService.saveAll(user5Offers);	

	}
}
