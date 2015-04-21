/*
 * Created on 9 avr. 2015 ( Time 11:50:16 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.silanis.lottery.persistence.services.fake;

import java.util.List;
import java.util.Map;

import com.silanis.lottery.bean.jpa.TicketEntity;
import com.silanis.lottery.persistence.commons.fake.GenericFakeService;
import com.silanis.lottery.persistence.services.TicketPersistence;

/**
 * Fake persistence service implementation ( entity "Ticket" )
 *
 * @author Telosys Tools Generator
 */
public class TicketPersistenceFAKE extends GenericFakeService<TicketEntity> implements TicketPersistence {

	public TicketPersistenceFAKE () {
		super(TicketEntity.class);
	}
	
	protected TicketEntity buildEntity(int index) {
		TicketEntity entity = new TicketEntity();
		// Init fields with mock values
		entity.setIdTicket( nextInteger() ) ;
		entity.setParticipantName( nextString() ) ;
		entity.setInscriptionDate( nextDate() ) ;
		entity.setPrice( nextInteger() ) ;
		return entity ;
	}
	
	
	public boolean delete(TicketEntity entity) {
		log("delete ( TicketEntity : " + entity + ")" ) ;
		return true;
	}

	public boolean delete( Integer idTicket ) {
		log("delete ( PK )") ;
		return true;
	}

	public void insert(TicketEntity entity) {
		log("insert ( TicketEntity : " + entity + ")" ) ;
	}

	public TicketEntity load( Integer idTicket ) {
		log("load ( PK )") ;
		return buildEntity(1) ; 
	}

	public List<TicketEntity> loadAll() {
		log("loadAll()") ;
		return buildList(40) ;
	}

	public List<TicketEntity> loadByNamedQuery(String queryName) {
		log("loadByNamedQuery ( '" + queryName + "' )") ;
		return buildList(20) ;
	}

	public List<TicketEntity> loadByNamedQuery(String queryName, Map<String, Object> queryParameters) {
		log("loadByNamedQuery ( '" + queryName + "', parameters )") ;
		return buildList(10) ;
	}

	public TicketEntity save(TicketEntity entity) {
		log("insert ( TicketEntity : " + entity + ")" ) ;
		return entity;
	}

	public List<TicketEntity> search(Map<String, Object> criteria) {
		log("search (criteria)" ) ;
		return buildList(15) ;
	}

	@Override
	public long countAll() {
		return 0 ;
	}

}