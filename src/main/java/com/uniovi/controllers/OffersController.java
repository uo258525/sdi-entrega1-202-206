package com.uniovi.controllers;

import java.security.Principal;
import java.util.LinkedList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.services.OffersService;
import com.uniovi.services.UserService;

@Controller
public class OffersController {

	@Autowired
	private HttpSession httpSession;

	@Autowired // Anotacion para Inyectar el servicio
	private OffersService offersService;

	@Autowired
	private UserService usersService;

	@RequestMapping("/offer/list")
	public String getList(Model model, Pageable pageable, Principal principal) {

		String email = principal.getName(); // DNI es el name de la
											// autenticación
		User user = usersService.getUserByEmail(email);
		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());

		
		offers = offersService.getOffersForUser(pageable, user);
		
		model.addAttribute("offerList", offers.getContent());
		model.addAttribute("page", offers); // Busca en
											// templates/fragments/offer
		return "offer/list";
	}

	@RequestMapping("/offer/search")
	public String getList(Model model, Pageable pageable, Principal principal,
			@RequestParam(value = "", required = false) String searchText) {

		String email = principal.getName();
		User user = usersService.getUserByEmail(email);
		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());

		if (searchText != null && !searchText.isEmpty()) {
			offers = offersService.searchOffersByDescriptionAndName(pageable, searchText);
		} else {
			offers = offersService.getOffersForUser(pageable, user);
		}
		model.addAttribute("offerList", offers.getContent());
		model.addAttribute("page", offers);

		return "offer/search";
	}

	
	@RequestMapping(value = "/offer/add", method = RequestMethod.POST)
	public String setoffer(@ModelAttribute Offer offer, Principal principal) {
		User user = usersService.getUserByEmail(principal.getName());
		offersService.addOffer(offer, user);
		return "redirect:/offer/list";
	}

	@RequestMapping(value = "/offer/add")
	public String getoffer(Model model) {
		// model.addAttribute("usersList", usersService.getUsers());
		return "offer/add";
	}

	@RequestMapping("/offer/details/{id}")
	public String getDetail(Model model, @PathVariable Long id) {
		model.addAttribute("offer", offersService.getOffer(id));
		return "offer/details";
	}

	@RequestMapping("/offer/delete/{id}")
	public String deleteoffer(@PathVariable Long id) {
		offersService.deleteOffer(id);
		return "redirect:/offer/list";
	}

	
	@RequestMapping("/offer/list/update")
	public String updateList(Model model, Pageable pageable, Principal principal) {
		String email = principal.getName();
		
		User user = usersService.getUserByEmail(email);

		Page<Offer> offers = offersService.getOffersForUser(pageable, user);
		model.addAttribute("offerList", offers.getContent());
		return "offer/list :: tableoffers";
	}
	@RequestMapping("/offer/list/buy/{id}")
	public String buyOffer(Model model, Pageable pageable,@PathVariable Long id, Principal principal) {
		Offer offer = offersService.getOffer(id);
		String email = principal.getName();
		// DNI es el name de la autenticación
		User user = usersService.getUserByEmail(email);
		user.buyOffer(offer);
		
		return "redirect:/offer/list";
	}
}
