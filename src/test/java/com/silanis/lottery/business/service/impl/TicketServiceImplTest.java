/*
 * Created on 9 avr. 2015 ( Time 11:50:32 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.silanis.lottery.business.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.silanis.lottery.bean.Ticket;
import com.silanis.lottery.bean.jpa.TicketEntity;
import java.util.Date;
import java.util.List;
import com.silanis.lottery.business.service.mapping.TicketServiceMapper;
import com.silanis.lottery.persistence.services.jpa.TicketPersistenceJPA;
import com.silanis.lottery.test.TicketFactoryForTest;
import com.silanis.lottery.test.TicketEntityFactoryForTest;
import com.silanis.lottery.test.MockValues;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Test : Implementation of TicketService
 */
@RunWith(MockitoJUnitRunner.class)
public class TicketServiceImplTest {

	@InjectMocks
	private TicketServiceImpl ticketService;
	@Mock
	private TicketPersistenceJPA ticketPersistenceJPA;
	@Mock
	private TicketServiceMapper ticketServiceMapper;
	
	private TicketFactoryForTest ticketFactoryForTest = new TicketFactoryForTest();

	private TicketEntityFactoryForTest ticketEntityFactoryForTest = new TicketEntityFactoryForTest();

	private MockValues mockValues = new MockValues();
	
	@Test
	public void findById() {
		// Given
		Integer idTicket = mockValues.nextInteger();
		
		TicketEntity ticketEntity = ticketPersistenceJPA.load(idTicket);
		
		Ticket ticket = ticketFactoryForTest.newTicket();
		when(ticketServiceMapper.mapTicketEntityToTicket(ticketEntity)).thenReturn(ticket);

		// When
		Ticket ticketFound = ticketService.findById(idTicket);

		// Then
		assertEquals(ticket.getIdTicket(),ticketFound.getIdTicket());
	}

	@Test
	public void findAll() {
		// Given
		List<TicketEntity> ticketEntitys = new ArrayList<TicketEntity>();
		TicketEntity ticketEntity1 = ticketEntityFactoryForTest.newTicketEntity();
		ticketEntitys.add(ticketEntity1);
		TicketEntity ticketEntity2 = ticketEntityFactoryForTest.newTicketEntity();
		ticketEntitys.add(ticketEntity2);
		when(ticketPersistenceJPA.loadAll()).thenReturn(ticketEntitys);
		
		Ticket ticket1 = ticketFactoryForTest.newTicket();
		when(ticketServiceMapper.mapTicketEntityToTicket(ticketEntity1)).thenReturn(ticket1);
		Ticket ticket2 = ticketFactoryForTest.newTicket();
		when(ticketServiceMapper.mapTicketEntityToTicket(ticketEntity2)).thenReturn(ticket2);

		// When
		List<Ticket> ticketsFounds = ticketService.findAll();

		// Then
		assertTrue(ticket1 == ticketsFounds.get(0));
		assertTrue(ticket2 == ticketsFounds.get(1));
	}

	@Test
	public void create() {
		// Given
		Ticket ticket = ticketFactoryForTest.newTicket();

		TicketEntity ticketEntity = ticketEntityFactoryForTest.newTicketEntity();
		when(ticketPersistenceJPA.load(ticket.getIdTicket())).thenReturn(null);
		
		ticketEntity = new TicketEntity();
		ticketServiceMapper.mapTicketToTicketEntity(ticket, ticketEntity);
		TicketEntity ticketEntitySaved = ticketPersistenceJPA.save(ticketEntity);
		
		Ticket ticketSaved = ticketFactoryForTest.newTicket();
		when(ticketServiceMapper.mapTicketEntityToTicket(ticketEntitySaved)).thenReturn(ticketSaved);

		// When
		Ticket ticketResult = ticketService.create(ticket);

		// Then
		assertTrue(ticketResult == ticketSaved);
	}

	@Test
	public void createKOExists() {
		// Given
		Ticket ticket = ticketFactoryForTest.newTicket();

		TicketEntity ticketEntity = ticketEntityFactoryForTest.newTicketEntity();
		when(ticketPersistenceJPA.load(ticket.getIdTicket())).thenReturn(ticketEntity);

		// When
		Exception exception = null;
		try {
			ticketService.create(ticket);
		} catch(Exception e) {
			exception = e;
		}

		// Then
		assertTrue(exception instanceof IllegalStateException);
		assertEquals("already.exists", exception.getMessage());
	}

	@Test
	public void update() {
		// Given
		Ticket ticket = ticketFactoryForTest.newTicket();

		TicketEntity ticketEntity = ticketEntityFactoryForTest.newTicketEntity();
		when(ticketPersistenceJPA.load(ticket.getIdTicket())).thenReturn(ticketEntity);
		
		TicketEntity ticketEntitySaved = ticketEntityFactoryForTest.newTicketEntity();
		when(ticketPersistenceJPA.save(ticketEntity)).thenReturn(ticketEntitySaved);
		
		Ticket ticketSaved = ticketFactoryForTest.newTicket();
		when(ticketServiceMapper.mapTicketEntityToTicket(ticketEntitySaved)).thenReturn(ticketSaved);

		// When
		Ticket ticketResult = ticketService.update(ticket);

		// Then
		verify(ticketServiceMapper).mapTicketToTicketEntity(ticket, ticketEntity);
		assertTrue(ticketResult == ticketSaved);
	}

	@Test
	public void delete() {
		// Given
		Integer idTicket = mockValues.nextInteger();

		// When
		ticketService.delete(idTicket);

		// Then
		verify(ticketPersistenceJPA).delete(idTicket);
		
	}

}
