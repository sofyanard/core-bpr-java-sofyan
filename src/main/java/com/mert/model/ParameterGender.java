package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "parametergender")
public class ParameterGender {
	
	@Id
	@Column(name = "gendercode")
	private String gendercode;
	
	@Column(name = "genderdesc")
	private String genderdesc;
	
	

	public String getGendercode() {
		return gendercode;
	}

	public String getGenderdesc() {
		return genderdesc;
	}
	
	
	
	public ParameterGender() { }

}
