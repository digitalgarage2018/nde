package it.iseed.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table (name="house")
public class House {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "city_fk")
	private City city;
//	
//	@Column
//	private String name;
	
	@Column
	private String address;
	
	@Column
	private String type;
	
	@Column
	private String E_class;
		
	@NotEmpty
	@Column
	private double latitude;
	
	@NotEmpty
	@Column
	private double longitude;
	
	@Column
	private double area;
	
	@Column
	private double price;
	
		

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getE_class() {
		return E_class;
	}

	public void setE_class(String e_class) {
		E_class = e_class;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
		
}
