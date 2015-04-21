/*
 * Created on 9 avr. 2015 ( Time 11:50:16 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.silanis.lottery.bean.jpa;
import java.io.Serializable;

import javax.persistence.*;

/**
 * Composite primary key for entity "WinnerEntity" ( stored in table "winner" )
 *
 * @author Telosys Tools Generator
 *
 */
 @Embeddable
public class WinnerEntityKey implements Serializable {
    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY KEY ATTRIBUTES 
    //----------------------------------------------------------------------
    @Column(name="id_ticket", nullable=false)
    private Integer    idTicket     ;
    
    @Column(name="id_draw", nullable=false)
    private Integer    idDraw       ;
    

    //----------------------------------------------------------------------
    // CONSTRUCTORS
    //----------------------------------------------------------------------
    public WinnerEntityKey() {
        super();
    }

    public WinnerEntityKey( Integer idTicket, Integer idDraw ) {
        super();
        this.idTicket = idTicket ;
        this.idDraw = idDraw ;
    }
    
    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR KEY FIELDS
    //----------------------------------------------------------------------
    public void setIdTicket( Integer value ) {
        this.idTicket = value;
    }
    public Integer getIdTicket() {
        return this.idTicket;
    }

    public void setIdDraw( Integer value ) {
        this.idDraw = value;
    }
    public Integer getIdDraw() {
        return this.idDraw;
    }


    //----------------------------------------------------------------------
    // equals METHOD
    //----------------------------------------------------------------------
	public boolean equals(Object obj) { 
		if ( this == obj ) return true ; 
		if ( obj == null ) return false ;
		if ( this.getClass() != obj.getClass() ) return false ; 
		WinnerEntityKey other = (WinnerEntityKey) obj; 
		//--- Attribute idTicket
		if ( idTicket == null ) { 
			if ( other.idTicket != null ) 
				return false ; 
		} else if ( ! idTicket.equals(other.idTicket) ) 
			return false ; 
		//--- Attribute idDraw
		if ( idDraw == null ) { 
			if ( other.idDraw != null ) 
				return false ; 
		} else if ( ! idDraw.equals(other.idDraw) ) 
			return false ; 
		return true; 
	} 


    //----------------------------------------------------------------------
    // hashCode METHOD
    //----------------------------------------------------------------------
	public int hashCode() { 
		final int prime = 31; 
		int result = 1; 
		
		//--- Attribute idTicket
		result = prime * result + ((idTicket == null) ? 0 : idTicket.hashCode() ) ; 
		//--- Attribute idDraw
		result = prime * result + ((idDraw == null) ? 0 : idDraw.hashCode() ) ; 
		
		return result; 
	} 


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() {
		StringBuffer sb = new StringBuffer(); 
		sb.append(idTicket); 
		sb.append("|"); 
		sb.append(idDraw); 
        return sb.toString();
    }
}