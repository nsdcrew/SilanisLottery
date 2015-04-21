/*
 * Created on 9 avr. 2015 ( Time 11:50:16 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */

package com.silanis.lottery.persistence.services.jpa;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.silanis.lottery.bean.jpa.TicketEntity;
import com.silanis.lottery.persistence.commons.jpa.GenericJpaService;
import com.silanis.lottery.persistence.commons.jpa.JpaOperation;
import com.silanis.lottery.persistence.services.TicketPersistence;

/**
 * JPA implementation for basic persistence operations ( entity "Ticket" )
 * 
 * @author Telosys Tools Generator
 *
 */
public class TicketPersistenceJPA extends GenericJpaService<TicketEntity, Integer> implements TicketPersistence {

	/**
	 * Constructor
	 */
	public TicketPersistenceJPA() {
		super(TicketEntity.class);
	}

	@Override
	public TicketEntity load( Integer idTicket ) {
		return super.load( idTicket );
	}

	@Override
	public boolean delete( Integer idTicket ) {
		return super.delete( idTicket );
	}

	@Override
	public boolean delete(TicketEntity entity) {
		if ( entity != null ) {
			return super.delete( entity.getIdTicket() );
		}
		return false ;
	}

	@Override
	public long countAll() {
		// JPA operation definition 
		JpaOperation operation = new JpaOperation() {
			@Override
			public Object exectue(EntityManager em) throws PersistenceException {
				Query query = em.createNamedQuery("TicketEntity.countAll");
				return query.getSingleResult() ;
			}
		} ;
		// JPA operation execution 
		return (Long) execute(operation);
	}

}