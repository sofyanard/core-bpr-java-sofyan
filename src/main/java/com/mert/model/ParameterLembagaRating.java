package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "parameterlembagarating")
public class ParameterLembagaRating {
	
	@Id
	@Column(name = "lembagarating_id")
	private String LembagaRatingId;
	
	@Column(name = "lembagarating_name")
	private String LembagaRatingName;
	
	@Column(name = "biojk_category")
	private String BiojkCategory;
	
	@Column(name = "biojk_code")
	private String BiojkCode;
	
	

	public String getLembagaRatingId() {
		return LembagaRatingId;
	}

	public void setLembagaRatingId(String lembagaRatingId) {
		LembagaRatingId = lembagaRatingId;
	}

	public String getLembagaRatingName() {
		return LembagaRatingName;
	}

	public void setLembagaRatingName(String lembagaRatingName) {
		LembagaRatingName = lembagaRatingName;
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
