package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bukubesar")
public class BukuBesar {
	
	@Id
	@Column(name = "bukubesar_id")
	private String BukuBesarId;
	
	@Column(name = "bukubesar_name")
	private String BukuBesarName;
	
	@Column(name = "account_type")
	private String AccountType;
	
	@Column(name = "bpr_code")
	private String BprCode;
	
	@Column(name = "unit_id")
	private String UnitId;
	
	@Column(name = "status")
	private String Status;
	
	
	
	// Getter and Setter

	public String getBukuBesarId() {
		return BukuBesarId;
	}

	public void setBukuBesarId(String bukuBesarId) {
		BukuBesarId = bukuBesarId;
	}

	public String getBukuBesarName() {
		return BukuBesarName;
	}

	public void setBukuBesarName(String bukuBesarName) {
		BukuBesarName = bukuBesarName;
	}

	public String getAccountType() {
		return AccountType;
	}

	public void setAccountType(String accountType) {
		AccountType = accountType;
	}

	public String getBprCode() {
		return BprCode;
	}

	public void setBprCode(String bprCode) {
		BprCode = bprCode;
	}

	public String getUnitId() {
		return UnitId;
	}

	public void setUnitId(String unitId) {
		UnitId = unitId;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}
	
	

}
