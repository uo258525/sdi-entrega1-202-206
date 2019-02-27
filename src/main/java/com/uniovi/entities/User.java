package com.uniovi.entities;

import java.util.Set;

import com.uniovi.entities.type.Rol;

public class User {

	private String email;
	private String name;
	private String surname;
	private String password;
	private double moneyAccount;
	private Rol rol;
	private Long id;
	private Set<Offer> date; 
	private Set<Offer> purchasedSales;
	
}