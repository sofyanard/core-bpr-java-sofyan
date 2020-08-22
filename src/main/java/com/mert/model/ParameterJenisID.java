package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "jenisid")
public class ParameterJenisID {
	
	@Id
	@Column(name = "jenisidcode")
	private String jenisidcode;
	
	@Column(name = "jenisiddesc")
	private String jenisiddesc;
	
	@Column(name = "kategori")
	private String kategori;
	
	@Column(name = "biojk")
	private String biojk;
	
	

	public String getJenisidcode() {
		return jenisidcode;
	}

	public String getJenisiddesc() {
		return jenisiddesc;
	}

	public String getKategori() {
		return kategori;
	}

	public String getBiojk() {
		return biojk;
	}
	
	
	
	public ParameterJenisID() { }

}
