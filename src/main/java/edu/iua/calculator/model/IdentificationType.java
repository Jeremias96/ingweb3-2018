package edu.iua.calculator.model;

import javax.persistence.*;

@Entity
@Table(name = "identification_type")
public class IdentificationType {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name = "identification_id", nullable = false)
	private int identificationId;
	
	@Column(name = "identification_name", length = 50)
	private String identificationName;
	
	public IdentificationType(){
		
	}

	public IdentificationType(int identificationId, String identificationName) {
		super();
		this.identificationId = identificationId;
		this.identificationName = identificationName;
	}

	public int getIdentification_id() {
		return identificationId;
	}

	public void setIdentification_id(int identificationId) {
		this.identificationId = identificationId;
	}

	public String getIdentificationName() {
		return identificationName;
	}

	public void setIdentificationName(String identificationName) {
		this.identificationName = identificationName;
	}
}
