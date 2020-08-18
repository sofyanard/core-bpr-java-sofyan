package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "kategoricatatan")
public class ParameterKategoriCatatan {
	
	@Id
	@Column(name = "catatancode")
	private String catatancode;
	
	@Column(name = "catatandesc")
	private String catatandesc;
	
	

	public String getCatatancode() {
		return catatancode;
	}

	public String getCatatandesc() {
		return catatandesc;
	}
	
	
	
	public ParameterKategoriCatatan() { }

}
