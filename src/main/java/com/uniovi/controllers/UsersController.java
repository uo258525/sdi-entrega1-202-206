package com.uniovi.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.User;
import com.uniovi.services.SecurityService;
import com.uniovi.services.UserService;
import com.uniovi.validators.SignUpFormValidator;

@Controller
public class UsersController {
	@Autowired
	private UserService usersService;
	
	@Autowired
	private SignUpFormValidator signUpFormValidator;
	
	@Autowired
	private SecurityService securityService;
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@Validated User user, BindingResult result, Model model) {
		signUpFormValidator.validate(user, result);
		if (result.hasErrors()) {
			return "signup";
		}
		
		usersService.addUser(user);
		securityService.autoLogin(user.getEmail(), user.getPassword2());
		return "redirect:home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}

	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String home(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = usersService.getUserByEmail(email);
		
		return "home";
	}
	
	@RequestMapping("/user/list")
	public String getList(Model model, Pageable pageable, Principal principal,
			@RequestParam(value = "", required = false) String searchText) {
		
		  String email = principal.getName(); // DNI es el name de la autenticación
		  User user = usersService.getUserByEmail(email); Page<User> offers = new
		  PageImpl<User>(new LinkedList<User>());
		  
		  if (searchText != null && !searchText.isEmpty()) { offers =
		  usersService.se(pageable,
		  searchText,user); }else { offers = usersService.getOffersForUser(pageable,
		  user); } model.addAttribute("offerList", offers.getContent());
		  model.addAttribute("page", offers); // Busca en templates/fragments/offer
		  return "offer/list";
	}
}
