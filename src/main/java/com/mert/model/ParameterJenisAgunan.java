package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "jenisagunan")
public class ParameterJenisAgunan {
	
	@Id
	@Column(name = "jenisagunan_id")
	private String JenisAgunanId;
	
	@Column(name = "jenisagunan_name")
	private String JenisAgunanName;
	
	@Column(name = "biojk_category")
	private String BiojkCategory;
	
	@Column(name = "biojk_code")
	private String BiojkCode;
	
	

	public String getJenisAgunanId() {
		return JenisAgunanId;
	}

	public void setJenisAgunanId(String jenisAgunanId) {
		JenisAgunanId = jenisAgunanId;
	}

	public String getJenisAgunanName() {
		return JenisAgunanName;
	}

	public void setJenisAgunanName(String jenisAgunanName) {
		JenisAgunanName = jenisAgunanName;
	}

	public String getBiojkCategory() {
		return BiojkCategory;
	}

	public void setBiojkCategory(String biojkCategory) {
		BiojkCategory = biojkCategory;
	}

	public String getBiojkCode() {
		return BiojkCode;
	}

	public void setBiojkCode(String biojkCode) {
		BiojkCode = biojkCode;
	}
	
	

}
