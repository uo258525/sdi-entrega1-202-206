package com.uniovi.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.uniovi.entities.type.Rol;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // genera
														// independientemente
														// del resto de tablas
	private Long id;

	@Column(unique = true)
	private String email;

	@Transient // no se guarda en la tabla
	private String password2;

	private String name;
	private String surname;
	private String password;
	private double moneyAccount;
	private boolean active;

	@Enumerated(EnumType.STRING)
	private Rol rol;
	
	@OneToMany (mappedBy="sender")
	private Set<Message> messagesSent=new HashSet<>();
	
	@OneToMany (mappedBy="receiver")
	private Set<Message> messagesReceived=new HashSet<>();

	@OneToMany(mappedBy = "owner")
	private Set<Offer> purchasedOffers = new HashSet<>(); // las ofertas que
															// compra el usuario

	@OneToMany(mappedBy = "buyer")
	private Set<Offer> announcedOffers = new HashSet<>(); // las ofertas
															// publicadas por el
															// usuario

	
	public User() {

	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getMoneyAccount() {
		return moneyAccount;
	}

	public void setMoneyAccount(double moneyAccount) {
		this.moneyAccount = moneyAccount;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Set<Offer> getPurchasedOffers() {
		return purchasedOffers;
	}

	public void setPurchasedOffers(Set<Offer> purchasedOffers) {
		this.purchasedOffers = purchasedOffers;
	}

	public Set<Offer> getAnnouncedOffers() {
		return announcedOffers;
	}

	public void setAnnouncedOffers(Set<Offer> announcedOffers) {
		this.announcedOffers = announcedOffers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password2="
				+ password2 + ", name=" + name + ", surname=" + surname
				+ ", password=" + password + ", moneyAccount=" + moneyAccount
				+ ", active=" + active + ", rol=" + rol + ", purchasedOffers="
				+ purchasedOffers + ", announcedOffers=" + announcedOffers
				+ "]";
	}

}