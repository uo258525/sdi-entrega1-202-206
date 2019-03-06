package com.uniovi.services;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.repositories.OfferRepository;

@Service
public class OffersService {
	@Autowired
	private HttpSession httpSession;

	@Autowired
	private OfferRepository offersRepository;

	public Page<Offer> getOffers(Pageable pageable) {
		Page<Offer> Offers = offersRepository.findAll(pageable);
		return Offers;
	}
	public Page<Offer> getOffersForUser(Pageable pageable, User user) {
		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());
		
		offers = getOffers(pageable);
		
		return offers;
	}

	public Offer getOffer(Long id) {
		Set<Offer> consultedList = (Set<Offer>) httpSession.getAttribute("consultedList");
		if (consultedList == null) {
			consultedList = new HashSet<Offer>();
		}
		Offer offerObtained = offersRepository.findById(id).get();
		consultedList.add(offerObtained);
		httpSession.setAttribute("consultedList", consultedList);
		return offerObtained;
	}

	public void addOffer(Offer offer) {
		// Si en Id es null le asignamos el ultimo + 1 de la lista
		offersRepository.save(offer);
	}

	public void deleteOffer(Long id) {
		offersRepository.deleteById(id);
	}



	public Page<Offer> searchOffersByDescriptionAndNameForUser(Pageable pageable, String searchText, User user) {
		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());
		searchText = "%" + searchText + "%";
		
		offers = offersRepository.searchByDescriptionAndName(pageable, searchText);
		
		
		return offers;
	}
}
	
