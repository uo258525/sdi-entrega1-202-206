package com.uniovi.entities;

import java.time.LocalDateTime;

import com.uniovi.entities.type.SaleStatus;

public class Offer {
	private long id;
	private String title;//object name	
	private String details; //details of the object
	private String description;//description of the object
	private LocalDateTime date; //date of objet upload
	private double price;
	private SaleStatus status; //offer status->possibility for the user to active and deactive an offer
	private boolean highlighted;//when an offer is highlighted
	private User owner;
	private User buyer;
}
