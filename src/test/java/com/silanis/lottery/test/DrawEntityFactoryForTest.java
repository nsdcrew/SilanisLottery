package com.silanis.lottery.test;

import com.silanis.lottery.bean.jpa.DrawEntity;

public class DrawEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public DrawEntity newDrawEntity() {

		Integer idDraw = mockValues.nextInteger();

		DrawEntity drawEntity = new DrawEntity();
		drawEntity.setIdDraw(idDraw);
		return drawEntity;
	}
	
}
