package com.mert.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@Table(name = "sandibiojk")
@IdClass(ParameterSandiBIOJKID.class)
public class ParameterSandiBIOJK implements Serializable {
	
	@Id
	@Column(name = "kategoricode")
	private String kategoricode;
	
	@Id
	@Column(name = "sandicode")
	private String sandicode;
	
	@Column(name = "sandidesc")
	private String sandidesc;
	
	public ParameterSandiBIOJK() { }

	public String getKategoricode() {
		return kategoricode;
	}

	public void setKategoricode(String kategoricode) {
		this.kategoricode = kategoricode;
	}

	public String getSandicode() {
		return sandicode;
	}

	public void setSandicode(String sandicode) {
		this.sandicode = sandicode;
	}

	public String getSandidesc() {
		return sandidesc;
	}

	public void setSandidesc(String sandidesc) {
		this.sandidesc = sandidesc;
	}
	
}
