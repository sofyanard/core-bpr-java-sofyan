package com.mert.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;

@Entity
@Table(name = "provinsi")
public class ParameterProvinsi {
	
	@Id
	@Column(name = "provinsicode")
	private String provinsicode;
	
	@Column(name = "provinsidesc")
	private String provinsidesc;
	
	public ParameterProvinsi() { }

	public String getProvinsicode() {
		return provinsicode;
	}

	public void setProvinsicode(String provinsicode) {
		this.provinsicode = provinsicode;
	}

	public String getProvinsidesc() {
		return provinsidesc;
	}

	public void setProvinsidesc(String provinsidesc) {
		this.provinsidesc = provinsidesc;
	}

}
