package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "parametermarital")
public class ParameterMarital {
	
	@Id
	@Column(name = "maritalcode")
	private String maritalcode;
	
	@Column(name = "maritaldesc")
	private String maritaldesc;
	
	@Column(name = "kategori")
	private String kategori;
	
	@Column(name = "biojk")
	private String biojk;
	
	

	public String getMaritalcode() {
		return maritalcode;
	}

	public String getMaritaldesc() {
		return maritaldesc;
	}

	public String getKategori() {
		return kategori;
	}

	public String getBiojk() {
		return biojk;
	}
	
	
	
	public ParameterMarital() { }

}
