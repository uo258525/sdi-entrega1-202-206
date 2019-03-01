package com.uniovi.entities;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.joda.time.LocalDateTime;

@Entity
public class Message {
	
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	
	private long id;
	
	private String content;
	
	private boolean valid;//podemos "borrar" mensajes. marcarlos como no válidos
	
	@ManyToOne
	private Offer offer;	
	@ManyToOne	
	private User sender;	
	@ManyToOne
	private User receiver;	
	private LocalDateTime date; //message date
	
	public Message() {
		
	}	
	

	public long getId() {
		return id;
	}



	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
	

}
