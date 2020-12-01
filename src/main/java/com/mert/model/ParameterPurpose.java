package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "parameterpurpose")
public class ParameterPurpose {
	
	@Id
	@Column(name = "purpose_id")
	private String PurposeId;
	
	@Column(name = "purpose_name")
	private String PurposeName;
	
	@Column(name = "biojk_category")
	private String BiojkCategory;
	
	@Column(name = "biojk_code")
	private String BiojkCode;
	
	

	public String getPurposeId() {
		return PurposeId;
	}

	public void setPurposeId(String purposeId) {
		PurposeId = purposeId;
	}

	public String getPurposeName() {
		return PurposeName;
	}

	public void setPurposeName(String purposeName) {
		PurposeName = purposeName;
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
	
	
	
	public ParameterPurpose() {
		
	}

}
