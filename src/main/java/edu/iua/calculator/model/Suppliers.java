package edu.iua.calculator.model;

import javax.persistence.*;

@Entity
@Table(name = "suppliers")
public class Suppliers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "client_id", nullable = false)
	private int supplier_id;
	
	@Column(name = "business_name", length = 50)
	private String business_name;
	
	public Suppliers(){
		
	}
	
	public Suppliers(int supplier_id, String business_name) {
		super();
		this.supplier_id = supplier_id;
		this.business_name = business_name;
	}

	public int getSupplier_id() {
		return supplier_id;
	}

	public void setSupplier_id(int supplier_id) {
		this.supplier_id = supplier_id;
	}

	public String getBusiness_name() {
		return business_name;
	}

	public void setBusiness_name(String business_name) {
		this.business_name = business_name;
	}
}
