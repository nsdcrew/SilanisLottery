package com.silanis.lottery.test;

import com.silanis.lottery.bean.Ticket;

public class TicketFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public Ticket newTicket() {

		Integer idTicket = mockValues.nextInteger();

		Ticket ticket = new Ticket();
		ticket.setIdTicket(idTicket);
		return ticket;
	}
	
}
