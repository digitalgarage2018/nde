/*
 ============================================================================
 Name        : UserEntity.java
 Author      : Alessio Onori
 Version     : 1.0
 Copyright   : Your copyright notice
 Description : Bean/entity che modella l'utente
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
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table (name="user")
public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Id                               //JPA id of the table
	@Column(name="id")                //JPA (if column name is different from variable name)
	@NotEmpty
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="username")
	@NotEmpty 
	private String username;

	@Column(name="password")
	@NotEmpty 
	private String password;

	@Column(name="email")
	@NotEmpty 
	private String email;
	
	@Transient//consente di non persistere l'attributo nel db
	private Wallet wallet;
	
	

	public User() {
		super();
	}

	public User(int id, String username, String password, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}
	
	public Wallet getWallet() {
		return wallet;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}
	
	
	

 
	
}
