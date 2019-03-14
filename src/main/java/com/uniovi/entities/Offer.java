package com.uniovi.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.joda.time.LocalDateTime;

import com.uniovi.entities.type.SaleStatus;

@Entity
public class Offer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String title;// object name
	private String description;// description of the object
	private LocalDateTime date; // date of objet upload
	private double price;
	

	@Enumerated(EnumType.STRING)
	private SaleStatus status; // offer status->possibility for the user to
								// active and deactive an offer

	@ManyToOne
	private User owner;
	@ManyToOne
	private User buyer;

	@OneToMany(mappedBy = "offer")
	private Set<Message> messages = new HashSet<>();

	public Offer() {

	}
	public Offer(String title,String description,double price, User owner)
	{
		this.title=title;
		this.description=description;
		this.price=price;
		this.owner = owner;
	}

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public SaleStatus getStatus() {
		return status;
	}

	public void setStatus(SaleStatus status) {
		this.status = status;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Offer other = (Offer) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Offer [id=" + id + ", title=" + title + ", description="
				+ description + ", date=" + date + ", price=" + price
				+ ", status=" + status + ", owner=" + owner + ", buyer=" + buyer
				+ ", messages=" + messages + "]";
	}

	

	

}
