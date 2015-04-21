/*
 * Created on 9 avr. 2015 ( Time 11:50:01 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.silanis.lottery.web.listitem;

import com.silanis.lottery.bean.Winner;
import com.silanis.lottery.web.common.ListItem;

public class WinnerListItem implements ListItem {

	private final String value ;
	private final String label ;
	
	public WinnerListItem(Winner winner) {
		super();

		this.value = ""
			 + winner.getIdTicket()
			 + "|"  + winner.getIdDraw()
		;

		//TODO : Define here the attributes to be displayed as the label
		this.label = winner.toString();
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public String getLabel() {
		return label;
	}

}