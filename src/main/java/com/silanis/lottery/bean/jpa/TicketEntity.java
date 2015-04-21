/*
 * Created on 9 avr. 2015 ( Time 11:50:16 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package com.silanis.lottery.bean.jpa;

import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Persistent class for entity stored in table "ticket"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="ticket", catalog="silanislottery" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="TicketEntity.countAll", query="SELECT COUNT(x) FROM TicketEntity x" )
} )
public class TicketEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id_ticket", nullable=false)
    private Integer    idTicket     ;


    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="participant_name", nullable=false, length=100)
    private String     participantName ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="inscription_date")
    private Date       inscriptionDate ;

    @Column(name="price", nullable=false)
    private Integer    price        ;

	// "idDraw" (column "id_draw") is not defined by itself because used as FK in a link 


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @ManyToOne
    @JoinColumn(name="id_draw", referencedColumnName="id_draw")
    private DrawEntity draw        ;

    @OneToMany(mappedBy="ticket", targetEntity=WinnerEntity.class)
    private List<WinnerEntity> listOfWinner;


    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public TicketEntity() {
		super();
    }
    
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
    //--- DATABASE MAPPING : participant_name ( VARCHAR ) 
    public void setParticipantName( String participantName ) {
        this.participantName = participantName;
    }
    public String getParticipantName() {
        return this.participantName;
    }

    //--- DATABASE MAPPING : inscription_date ( DATETIME ) 
    public void setInscriptionDate( Date inscriptionDate ) {
        this.inscriptionDate = inscriptionDate;
    }
    public Date getInscriptionDate() {
        return this.inscriptionDate;
    }

    //--- DATABASE MAPPING : price ( INT ) 
    public void setPrice( Integer price ) {
        this.price = price;
    }
    public Integer getPrice() {
        return this.price;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------
    public void setDraw( DrawEntity draw ) {
        this.draw = draw;
    }
    public DrawEntity getDraw() {
        return this.draw;
    }

    public void setListOfWinner( List<WinnerEntity> listOfWinner ) {
        this.listOfWinner = listOfWinner;
    }
    public List<WinnerEntity> getListOfWinner() {
        return this.listOfWinner;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(idTicket);
        sb.append("]:"); 
        sb.append(participantName);
        sb.append("|");
        sb.append(inscriptionDate);
        sb.append("|");
        sb.append(price);
        return sb.toString(); 
    } 

}
