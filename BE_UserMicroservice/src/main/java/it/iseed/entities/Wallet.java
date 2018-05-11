/*
 ============================================================================
 Name        : WalletEntity.java
 Author      : Alessio Onori
 Version     : 1.0
 Copyright   : Your copyright notice
 Description : Bean/entity che modella il wallet
 ============================================================================
 */

package it.iseed.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table (name="wallet")
public class Wallet implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Id                               //JPA id of the table
	@Column(name="id")                //JPA (if column name is different from variable name)
	@NotEmpty
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="id_user")
	@NotEmpty 
	private int idUser;

	@Column(name="credit")
	@NotEmpty 
	private double credit;

	public Wallet() {
		super();
	}

	public Wallet(int id, int idUser, double credit) {
		super();
		this.id = id;
		this.idUser = idUser;
		this.credit = credit;
	}

	public int getId() {
		return id;
	}

	public int getIdUser() {
		return idUser;
	}

	public double getCredit() {
		return credit;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

	
	

	
	
	
	

 
	
}
