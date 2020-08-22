package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "badanusaha")
public class ParameterBadanUsaha {
	
	@Id
	@Column(name = "bucode")
	private String bucode;
	
	@Column(name = "budesc")
	private String budesc;
	
	@Column(name = "kategori")
	private String kategori;
	
	@Column(name = "biojk")
	private String biojk;
	
	

	public String getBucode() {
		return bucode;
	}

	public String getBudesc() {
		return budesc;
	}

	public String getKategori() {
		return kategori;
	}

	public String getBiojk() {
		return biojk;
	}
	
	
	
	public ParameterBadanUsaha() { }

}
