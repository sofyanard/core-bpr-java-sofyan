package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "parameterkolektibilitas")
public class ParameterKolektibilitas {
	
	@Id
	@Column(name = "kolektibilitas_id")
	private String KolektibilitasId;
	
	@Column(name = "kolektibilitas_name")
	private String KolektibilitasName;
	
	@Column(name = "biojk_category")
	private String BiojkCategory;
	
	@Column(name = "biojk_code")
	private String BiojkCode;
	
	

	public String getKolektibilitasId() {
		return KolektibilitasId;
	}

	public void setKolektibilitasId(String kolektibilitasId) {
		KolektibilitasId = kolektibilitasId;
	}

	public String getKolektibilitasName() {
		return KolektibilitasName;
	}

	public void setKolektibilitasName(String kolektibilitasName) {
		KolektibilitasName = kolektibilitasName;
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
	
	
	
	public ParameterKolektibilitas() {
		
	}

}
