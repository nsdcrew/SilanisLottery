/*
 * Created on 9 avr. 2015 ( Time 11:50:01 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.silanis.lottery.bean;

import java.io.Serializable;

import javax.validation.constraints.*;

import java.util.Date;

public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    private Integer idTicket;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @NotNull
    @Size( min = 1, max = 100 )
    private String participantName;

    @NotNull
    private Date inscriptionDate;

    @NotNull
    private Integer price;

    @NotNull
    private Integer idDraw;



    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setIdTicket( Integer idTicket ) {
        this.idTicket = idTicket ;
    }

    public Integer getIdTicket() {
        return this.idTicket;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    public void setParticipantName( String participantName ) {
        this.participantName = participantName;
    }
    public String getParticipantName() {
        return this.participantName;
    }

    public void setInscriptionDate( Date inscriptionDate ) {
        this.inscriptionDate = inscriptionDate;
    }
    public Date getInscriptionDate() {
        return this.inscriptionDate;
    }

    public void setPrice( Integer price ) {
        this.price = price;
    }
    public Integer getPrice() {
        return this.price;
    }

    public void setIdDraw( Integer idDraw ) {
        this.idDraw = idDraw;
    }
    public Integer getIdDraw() {
        return this.idDraw;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
 
        public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(idTicket);
        sb.append("|");
        sb.append(participantName);
        sb.append("|");
        sb.append(inscriptionDate);
        sb.append("|");
        sb.append(price);
        sb.append("|");
        sb.append(idDraw);
        return sb.toString(); 
    } 


}
