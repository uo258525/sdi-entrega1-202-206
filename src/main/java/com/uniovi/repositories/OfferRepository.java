package com.uniovi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.entities.type.SaleStatus;


public interface OfferRepository extends JpaRepository<Offer, Long> {

	@Query("SELECT r FROM Offer r WHERE r.owner = ?1 ORDER BY r.id ASC ")
	Page<Offer> findAllByUser(Pageable pageable, User user);

	@Query("SELECT r FROM Offer r WHERE (LOWER(r.title) "
			+ "LIKE LOWER(?1) OR LOWER(r.owner.name) LIKE LOWER(?1))")
	Page<Offer> searchByDescriptionAndName(Pageable pageable, String seachtext);

	@Query("SELECT r FROM Offer r WHERE (LOWER(r.description) "
			+ "LIKE LOWER(?1) OR  LOWER(r.owner.name) "
			+ "LIKE LOWER(?1)) AND r.owner = ?2")
	Page<Offer> searchByDescriptionNameAndUser(Pageable pageable,
			String seachtext, User user);

	Page<Offer> findAll(Pageable pageable);
	
	@Query("SELECT s FROM Offer s WHERE s.status ='AVAILABLE'OR "
			+ "s.status='HIGHLIGHTED' AND s.owner.id != ?1")
	Page<Offer> findToSell (Pageable pageable, Long id);
	
	Page<Offer> findByOwnerIdAndStatusIsNot(Pageable pageable, Long id, SaleStatus status);

	Page<Offer> findByOwnerIdIsNotAndStatusIsNot(Pageable pageable, Long id, SaleStatus status);

	@Query("SELECT r FROM Offer r WHERE r.buyer = ?1 ORDER BY r.id ASC ")
	Page<Offer> findBoughtByUser(Pageable pageable, User user);

}
