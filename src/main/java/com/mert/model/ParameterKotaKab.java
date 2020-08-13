package com.mert.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;

@Entity
@Table(name = "kotakabupaten")
public class ParameterKotaKab {
	
	@Id
	@Column(name = "kotacode")
	public String kotacode;
	
	@Column(name = "provinsicode")
	public String provinsicode;
	
	@Column(name = "kotadesc")
	public String kotadesc;
	
	public ParameterKotaKab() { }

	public String getKotacode() {
		return kotacode;
	}

	public void setKotacode(String kotacode) {
		this.kotacode = kotacode;
	}

	public String getProvinsicode() {
		return provinsicode;
	}

	public void setProvinsicode(String provinsicode) {
		this.provinsicode = provinsicode;
	}

	public String getKotadesc() {
		return kotadesc;
	}

	public void setKotadesc(String kotadesc) {
		this.kotadesc = kotadesc;
	}
	
}
