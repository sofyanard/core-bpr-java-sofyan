package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "parametergolonganpenjamin")
public class ParameterGolonganPenjamin {
	
	@Id
	@Column(name = "golpenjamin_id")
	private String GolPenjaminId;
	
	@Column(name = "golpenjamin_name")
	private String GolPenjaminName;
	
	@Column(name = "biojk_category")
	private String BiojkCategory;
	
	@Column(name = "biojk_code")
	private String BiojkCode;
	
	

	public String getGolPenjaminId() {
		return GolPenjaminId;
	}

	public void setGolPenjaminId(String golPenjaminId) {
		GolPenjaminId = golPenjaminId;
	}

	public String getGolPenjaminName() {
		return GolPenjaminName;
	}

	public void setGolPenjaminName(String golPenjaminName) {
		GolPenjaminName = golPenjaminName;
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
