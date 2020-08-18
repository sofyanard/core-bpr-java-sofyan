package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "kodedokumen")
public class ParameterKodeDokumen {
	
	@Id
	@Column(name = "dokcode")
	private String dokcode;
	
	@Column(name = "dokdesc")
	private String dokdesc;
	
	

	public String getDokcode() {
		return dokcode;
	}

	public String getDokdesc() {
		return dokdesc;
	}
	
	
	
	public ParameterKodeDokumen() { }

}
