package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "eodlapkeu")
public class EodLapKeu {
	
	@Id
	@Column(name = "lapkeu_id")
	private String LapKeuId;
	
	@Column(name = "lapkeu_name")
	private String LapKeuName;
	
	@Column(name = "eom_only")
	private String EomOnly;
	
	

	public String getLapKeuId() {
		return LapKeuId;
	}

	public void setLapKeuId(String lapKeuId) {
		LapKeuId = lapKeuId;
	}

	public String getLapKeuName() {
		return LapKeuName;
	}

	public void setLapKeuName(String lapKeuName) {
		LapKeuName = lapKeuName;
	}
	
	public String getEomOnly() {
		return EomOnly;
	}

	public void setEomOnly(String eomOnly) {
		EomOnly = eomOnly;
	}

}
