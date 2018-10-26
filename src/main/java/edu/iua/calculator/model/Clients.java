package edu.iua.calculator.model;

import javax.persistence.*;

@Entity
@Table(name = "clients")
public class Clients {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "client_id", nullable = false)
	private int clientId;
	
	@Column(name = "name", length = 50)
	private String name;

	@Column(name = "last_name", length = 50)
	private String lastName;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id")
	private Addresses address;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "identification_id")
	private IdentificationType identificationType;

	public Clients(){
		
	}

	public Clients(int clientId, String name, String lastName, Addresses address) {
		super();
		this.clientId = clientId;
		this.name = name;
		this.lastName = lastName;
		this.address = address;
	}

	public int getClient_id() {
		return clientId;
	}

	public void setClient_id(int clientId) {
		this.clientId = clientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLast_name(String lastName) {
		this.lastName = lastName;
	}

	public Addresses getAddress() {
		return address;
	}

	public void setAddress(Addresses address) {
		this.address = address;
	}
	
	public String toString() {
	    return "Name: '" + this.name + "', Last Name: '" + this.lastName;
	}
}
