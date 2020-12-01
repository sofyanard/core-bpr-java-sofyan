package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "parameterbank")
public class ParameterBank {
	
	@Id
	@Column(name = "bank_id")
	private String BankId;
	
	@Column(name = "bank_name")
	private String BankName;
	
	@Column(name = "biojk_category")
	private String BiojkCategory;
	
	@Column(name = "biojk_code")
	private String BiojkCode;
	
	

	public String getBankId() {
		return BankId;
	}

	public void setBankId(String bankId) {
		BankId = bankId;
	}

	public String getBankName() {
		return BankName;
	}

	public void setBankName(String bankName) {
		BankName = bankName;
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
	
	
	
	public ParameterBank() {
		
	}

}
