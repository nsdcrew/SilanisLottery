/*
 * Created on 9 avr. 2015 ( Time 11:50:16 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.silanis.lottery.persistence.services.fake;

import java.util.List;
import java.util.Map;

import com.silanis.lottery.bean.jpa.DrawEntity;
import com.silanis.lottery.persistence.commons.fake.GenericFakeService;
import com.silanis.lottery.persistence.services.DrawPersistence;

/**
 * Fake persistence service implementation ( entity "Draw" )
 *
 * @author Telosys Tools Generator
 */
public class DrawPersistenceFAKE extends GenericFakeService<DrawEntity> implements DrawPersistence {

	public DrawPersistenceFAKE () {
		super(DrawEntity.class);
	}
	
	protected DrawEntity buildEntity(int index) {
		DrawEntity entity = new DrawEntity();
		// Init fields with mock values
		entity.setIdDraw( nextInteger() ) ;
		entity.setCreationDate( nextDate() ) ;
		entity.setDrawDate( nextDate() ) ;
		entity.setIsOpen( false ) ;
		return entity ;
	}
	
	
	public boolean delete(DrawEntity entity) {
		log("delete ( DrawEntity : " + entity + ")" ) ;
		return true;
	}

	public boolean delete( Integer idDraw ) {
		log("delete ( PK )") ;
		return true;
	}

	public void insert(DrawEntity entity) {
		log("insert ( DrawEntity : " + entity + ")" ) ;
	}

	public DrawEntity load( Integer idDraw ) {
		log("load ( PK )") ;
		return buildEntity(1) ; 
	}
	
	public DrawEntity loadIncludingParticipantAndWinners( Integer idDraw ) {
		log("loadIncludingParticipantAndWinners ( PK )") ;
		return buildEntity(1) ; 
	}

	public List<DrawEntity> loadAll() {
		log("loadAll()") ;
		return buildList(40) ;
	}

	public List<DrawEntity> loadByNamedQuery(String queryName) {
		log("loadByNamedQuery ( '" + queryName + "' )") ;
		return buildList(20) ;
	}

	public List<DrawEntity> loadByNamedQuery(String queryName, Map<String, Object> queryParameters) {
		log("loadByNamedQuery ( '" + queryName + "', parameters )") ;
		return buildList(10) ;
	}

	public DrawEntity save(DrawEntity entity) {
		log("insert ( DrawEntity : " + entity + ")" ) ;
		return entity;
	}

	public List<DrawEntity> search(Map<String, Object> criteria) {
		log("search (criteria)" ) ;
		return buildList(15) ;
	}

	@Override
	public long countAll() {
		return 0 ;
	}

}
