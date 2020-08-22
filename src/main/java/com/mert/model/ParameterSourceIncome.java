package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "parametersourceincome")
public class ParameterSourceIncome {
	
	@Id
	@Column(name = "incomecode")
	private String incomecode;
	
	@Column(name = "incomedesc")
	private String incomedesc;
	
	

	public String getIncomecode() {
		return incomecode;
	}

	public String getIncomedesc() {
		return incomedesc;
	}
	
	
	
	public ParameterSourceIncome() { }

}
