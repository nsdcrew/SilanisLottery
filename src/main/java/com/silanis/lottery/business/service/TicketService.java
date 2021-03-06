/*
 * Created on 9 avr. 2015 ( Time 11:50:32 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.silanis.lottery.business.service;

import java.util.List;

import com.silanis.lottery.bean.Ticket;

/**
 * Business Service Interface for entity Ticket.
 */
public interface TicketService { 

	/**
	 * Loads an entity from the database using its Primary Key
	 * @param idTicket
	 * @return entity
	 */
	Ticket findById( Integer idTicket  ) ;

	/**
	 * Loads all entities.
	 * @return all entities
	 */
	List<Ticket> findAll();

	/**
	 * Saves the given entity in the database (create or update)
	 * @param entity
	 * @return entity
	 */
	Ticket save(Ticket entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return
	 */
	Ticket update(Ticket entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	Ticket create(Ticket entity);

	/**
	 * Deletes an entity using its Primary Key
	 * @param idTicket
	 */
	void delete( Integer idTicket );


}
