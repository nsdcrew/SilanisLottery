package com.silanis.lottery.test;

import com.silanis.lottery.bean.Winner;

public class WinnerFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public Winner newWinner() {

		Integer idTicket = mockValues.nextInteger();
		Integer idDraw = mockValues.nextInteger();

		Winner winner = new Winner();
		winner.setIdTicket(idTicket);
		winner.setIdDraw(idDraw);
		return winner;
	}
	
}
