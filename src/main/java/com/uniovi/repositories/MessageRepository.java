package com.uniovi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uniovi.entities.Message;

public interface MessageRepository extends JpaRepository <Message, Long> {

}
