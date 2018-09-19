package edu.iua.calculator.model;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
public class Addresses {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name = "address_id", nullable = false)
	private int address_id;
	
	@Column(name = "street", length = 100)
	private String street;
	
	@Column(name = "number")
	private int number;
	
	@Column(name = "city", length = 50)
	private String city;
	
	@Column(name = "state", length = 50)
	private String state;
	
	@Column(name = "country", length = 50)
	private String country;
	
	@Column(name = "zip_code")
	private int zip_code;
	
	public Addresses(){
		
	}
	
	public Addresses(int address_id, String street, int number, String city, String state, String country,
			int zip_code) {
		super();
		this.address_id = address_id;
		this.street = street;
		this.number = number;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zip_code = zip_code;
	}

	public int getAddress_id() {
		return address_id;
	}

	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getZip_code() {
		return zip_code;
	}

	public void setZip_code(int zip_code) {
		this.zip_code = zip_code;
	}
}
