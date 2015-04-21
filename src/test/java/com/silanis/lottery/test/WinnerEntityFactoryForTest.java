package com.silanis.lottery.test;

import com.silanis.lottery.bean.jpa.WinnerEntity;

public class WinnerEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public WinnerEntity newWinnerEntity() {

		Integer idTicket = mockValues.nextInteger();
		Integer idDraw = mockValues.nextInteger();

		WinnerEntity winnerEntity = new WinnerEntity();
		winnerEntity.setIdTicket(idTicket);
		winnerEntity.setIdDraw(idDraw);
		return winnerEntity;
	}
	
}
