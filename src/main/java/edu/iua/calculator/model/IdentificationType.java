package edu.iua.calculator.model;

import javax.persistence.*;

@Entity
@Table(name = "identification_type")
public class IdentificationType {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name = "identification_id", nullable = false)
	private int identification_id;
	
	public IdentificationType(){
		
	}

	public IdentificationType(int identification_id) {
		super();
		this.identification_id = identification_id;
	}

	public int getIdentification_id() {
		return identification_id;
	}

	public void setIdentification_id(int identification_id) {
		this.identification_id = identification_id;
	}
}
