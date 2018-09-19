package edu.iua.calculator.model;

import javax.persistence.*;

@Entity
@Table(name = "clients")
public class Clients {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "client_id", nullable = false)
	private int client_id;
	
	@Column(name = "name", length = 50)
	private String name;

	@Column(name = "last_name", length = 50)
	private String last_name;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id")
	private Addresses address;

	public Clients(){
		
	}

	public Clients(int client_id, String name, String last_name, Addresses address) {
		super();
		this.client_id = client_id;
		this.name = name;
		this.last_name = last_name;
		this.address = address;
	}

	public int getClient_id() {
		return client_id;
	}

	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public Addresses getAddress() {
		return address;
	}

	public void setAddress(Addresses address) {
		this.address = address;
	}
}
