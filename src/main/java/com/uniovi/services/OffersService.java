package com.uniovi.services;

import java.util.LinkedList;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.entities.type.SaleStatus;
import com.uniovi.repositories.OfferRepository;
import com.uniovi.repositories.UserRepository;

@Service
public class OffersService {

	@Autowired
	private OfferRepository offersRepository;

	@Autowired
	private UserRepository userRepository;

	public Page<Offer> getOffers(Pageable pageable) {
		Page<Offer> Offers = offersRepository.findAll(pageable);
		return Offers;
	}

	public Page<Offer> getOffersForUser(Pageable pageable, User user) {
		return offersRepository.findByOwnerIdIsNotAndStatusIsNot(pageable,user.getId(),
				SaleStatus.SOLD);
	}
	
	public Page<Offer> getOffersOwn(Pageable pageable, User user) {
		return offersRepository.findAllByUser(pageable,user);
	}
	public Page<Offer> getOffersBought(Pageable pageable, User user) {
		return offersRepository.findBoughtByUser(pageable,user);
	}

	public Offer getOffer(Long id) {
		return offersRepository.getOne(id);
	}

	public void addOffer(Offer offer, User user) {
		offer.setOwner(user);
		offer.setStatus(SaleStatus.AVAILABLE);
		offer.setDate(new LocalDateTime());
		offersRepository.save(offer);
	}

	public void deleteOffer(Long id) {
		//offersRepository.deleteById(id);
		Offer offer = getOffer(id);
		offer.setStatus(SaleStatus.NOTAVAILABLE);
	}

	public Page<Offer> searchOffersByDescriptionAndName(Pageable pageable,
			String searchText) {
		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());
		searchText = "%" + searchText + "%";
		offers = offersRepository.searchByDescriptionAndName(pageable,
				searchText);
		return offers;
	}

	public boolean buyOffer(Offer offer, User user) {
		boolean result = user.buyOffer(offer);
		if (result) {
			offersRepository.save(offer);
			userRepository.save(user);
		}
		return result;
	}

	
}
