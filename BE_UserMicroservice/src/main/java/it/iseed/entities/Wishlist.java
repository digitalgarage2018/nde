/*
 ============================================================================
 Name        : WishList.java
 Author      : Alessio Onori
 Version     : 1.0
 Copyright   : Your copyright notice
 Description : Bean/entity che modella la wish list
 ============================================================================
 */

package it.iseed.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table (name="whislist")
public class Wishlist implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Id                               //JPA id of the table
	@Column(name="id")                //JPA (if column name is different from variable name)
	@NotEmpty
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
//	private List<House> houses;
	

	public Wishlist() {
		super();
	}


	public Wishlist(int id, List<House> houses) {
		super();
		this.id = id;
//		this.houses = houses;
	}


	public int getId() {
		return id;
	}


//	public List<House> getHouses() {
//		return houses;
//	}


	public void setId(int id) {
		this.id = id;
	}


//	public void setHouses(List<House> houses) {
//		this.houses = houses;
//	}
	
	

	

	
	

	
	
	
	

 
	
}
