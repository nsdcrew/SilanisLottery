/*
 * Created on 9 avr. 2015 ( Time 11:50:01 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.silanis.lottery.web.common;

public enum MessageType {
	
	SUCCESS,
	INFO,
	WARNING,
	DANGER;
	
	public String getCss() {
		return name().toLowerCase();
	}
	
}
