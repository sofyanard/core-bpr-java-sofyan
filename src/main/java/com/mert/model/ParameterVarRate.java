package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "varrate")
public class ParameterVarRate {
	
	@Id
	@Column(name = "varrate_id")
	private String VarRateId;
	
	@Column(name = "varrate_name")
	private String VarRateName;
	
	

	public String getVarRateId() {
		return VarRateId;
	}

	public void setVarRateId(String varRateId) {
		VarRateId = varRateId;
	}

	public String getVarRateName() {
		return VarRateName;
	}

	public void setVarRateName(String varRateName) {
		VarRateName = varRateName;
	}
	
	
	
	public ParameterVarRate() {
		
	}

}
