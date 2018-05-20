/*
 ============================================================================
 Name        : WishList.java
 Author      : Alessio Onori
 Version     : 1.0
 Copyright   : Your copyright notice
 Description : Bean/entity che modella la wish list
 al momento 1 a 1 con verso user, ma facilmente estendibile a uno a molti
 nel caso si voglia dotare l'user di più wishlist 
 ============================================================================
 */

package it.iseed.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table (name="wishlist")
public class Wishlist implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Id                               //JPA id of the table
	@Column(name="id", nullable=false)               //JPA (if column name is different from variable name)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="name")
	private String name;
	
	@OneToOne
	private User user;
	
	@ManyToMany
    private List<House> houses = new ArrayList<>();
	

	public Wishlist() {
		super();
	}


	public Wishlist(int id, List<House> houses) {
		super();
		this.id = id;
		this.houses = houses;
	}


	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}


	public List<House> getHouses() {
		return houses;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setHouses(List<House> houses) {
		this.houses = houses;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
	

	

	
	

	
	
	
	

 
	
}
