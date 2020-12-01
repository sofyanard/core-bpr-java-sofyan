package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "parameterpengikatan")
public class ParameterPengikatan {
	
	@Id
	@Column(name = "pengikatan_id")
	private String PengikatanId;
	
	@Column(name = "pengikatan_name")
	private String PengikatanName;
	
	@Column(name = "biojk_category")
	private String BiojkCategory;
	
	@Column(name = "biojk_code")
	private String BiojkCode;
	
	

	public String getPengikatanId() {
		return PengikatanId;
	}

	public void setPengikatanId(String pengikatanId) {
		PengikatanId = pengikatanId;
	}

	public String getPengikatanName() {
		return PengikatanName;
	}

	public void setPengikatanName(String pengikatanName) {
		PengikatanName = pengikatanName;
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
