package com.silanis.lottery.test;

import com.silanis.lottery.bean.jpa.TicketEntity;

public class TicketEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public TicketEntity newTicketEntity() {

		Integer idTicket = mockValues.nextInteger();

		TicketEntity ticketEntity = new TicketEntity();
		ticketEntity.setIdTicket(idTicket);
		return ticketEntity;
	}
	
}
