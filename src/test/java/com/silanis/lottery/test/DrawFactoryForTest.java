package com.silanis.lottery.test;

import com.silanis.lottery.bean.Draw;

public class DrawFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public Draw newDraw() {

		Integer idDraw = mockValues.nextInteger();

		Draw draw = new Draw();
		draw.setIdDraw(idDraw);
		return draw;
	}
	
}
