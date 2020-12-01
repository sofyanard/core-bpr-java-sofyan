package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "parameterpinalti")
public class ParameterPinalti {
	
	@Id
	@Column(name = "pinalti_id")
	private String PinaltiId;
	
	@Column(name = "pinalti_name")
	private String PinaltiName;
	
	

	public String getPinaltiId() {
		return PinaltiId;
	}

	public void setPinaltiId(String pinaltiId) {
		PinaltiId = pinaltiId;
	}

	public String getPinaltiName() {
		return PinaltiName;
	}

	public void setPinaltiName(String pinaltiName) {
		PinaltiName = pinaltiName;
	}
	
	
	
	public ParameterPinalti() {
		
	}

}
