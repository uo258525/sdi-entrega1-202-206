package com.uniovi.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.services.OffersService;
import com.uniovi.services.UserService;

@Controller
public class OffersController {

	@Autowired // Anotacion para Inyectar el servicio
	private OffersService offersService;

	@Autowired
	private UserService usersService;

	@GetMapping("/offer/list")
	public String getList(Model model, Pageable pageable, Principal principal) {

		String email = principal.getName();
		User user = usersService.getUserByEmail(email);
		Page<Offer> offers = offersService.getOffersForUser(pageable, user);
		model.addAttribute("moneyAccount", user.getMoneyAccount());
		model.addAttribute("offerList", offers.getContent());
		model.addAttribute("page", offers);
		return "offer/list";
	}

	@GetMapping("/offer/selling")
	public String getSellingList(Model model, Pageable pageable,
			Principal principal) {

		String email = principal.getName();
		User user = usersService.getUserByEmail(email);
		Page<Offer> offers = offersService.getOffersOwn(pageable, user);
		model.addAttribute("moneyAccount", user.getMoneyAccount());
		model.addAttribute("page", offers);
		model.addAttribute("offerList", offers.getContent());
		return "offer/selling";
	}

	@GetMapping("/offer/bought")
	public String getBoughtList(Model model, Pageable pageable,
			Principal principal) {

		String email = principal.getName();
		User user = usersService.getUserByEmail(email);
		Page<Offer> offers = offersService.getOffersBought(pageable, user);
		model.addAttribute("moneyAccount", user.getMoneyAccount());
		model.addAttribute("offerList", offers.getContent());
		model.addAttribute("page", offers);
		return "offer/bought";
	}

	@GetMapping("/offer/search")
	public String getList(Model model, Pageable pageable, Principal principal,
			@RequestParam(value = "", required = false) String searchText) {

		String email = principal.getName();
		User user = usersService.getUserByEmail(email);
		Page<Offer> offers;

		if (searchText != null && !searchText.isEmpty()) {
			offers = offersService.searchOffersByDescriptionAndName(pageable,
					searchText);
		} else {
			offers = offersService.getOffersForUser(pageable, user);
		}
		model.addAttribute("moneyAccount", user.getMoneyAccount());
		model.addAttribute("offerList", offers.getContent());
		model.addAttribute("page", offers);

		return "offer/search";
	}

	@PostMapping("/offer/add")
	public String setoffer(@ModelAttribute Offer offer, Principal principal) {
		User user = usersService.getUserByEmail(principal.getName());
		offersService.addOffer(offer, user);
		return "redirect:/offer/list";
	}

	@GetMapping("/offer/add")
	public String getoffer(Model model, Principal principal) {
		User user = usersService.getUserByEmail(principal.getName());
		model.addAttribute("moneyAccount", user.getMoneyAccount());
		return "offer/add";
	}

	@GetMapping("/offer/details/{id}")
	public String getDetail(Model model, Principal principal,
			@PathVariable Long id) {
		User user = usersService.getUserByEmail(principal.getName());
		Offer offer = offersService.getOffer(id);
		if (offer.getOwner().equals(user)) {
			model.addAttribute("owner", true);
		} else {
			model.addAttribute("owner", false);
		}
		model.addAttribute("moneyAccount", user.getMoneyAccount());
		model.addAttribute("offer", offer);
		return "offer/details";
	}

	@GetMapping("/offer/delete/{id}")
	public String deleteoffer(@PathVariable Long id) {
		offersService.deleteOffer(id);
		return "redirect:/offer/selling";
	}

	@GetMapping("/offer/list/update")
	public String updateList(Model model, Pageable pageable,
			Principal principal) {
		String email = principal.getName();
		User user = usersService.getUserByEmail(email);
		Page<Offer> offers = offersService.getOffersForUser(pageable, user);
		model.addAttribute("offerList", offers.getContent());
		return "offer/list :: tableoffers";
	}

	@GetMapping("/offer/list/buy/{id}")
	public String buyOffer(Model model, Pageable pageable,
			@PathVariable Long id, Principal principal) {
		Offer offer = offersService.getOffer(id);
		String email = principal.getName();
		User user = usersService.getUserByEmail(email);
		boolean result = offersService.buyOffer(offer, user);
		if (result) {
			return "redirect:/offer/list?bought";
		}
		return "redirect:/offer/list?error";
	}

}
