package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "eodlapkeuunit")
public class EodLapKeuUnit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer Id;
	
	@Column(name = "lapkeu_id")
	private String LapKeuId;
	
	@Column(name = "unit_id")
	private String UnitId;
	
	

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getLapKeuId() {
		return LapKeuId;
	}

	public void setLapKeuId(String lapKeuId) {
		LapKeuId = lapKeuId;
	}

	public String getUnitId() {
		return UnitId;
	}

	public void setUnitId(String unitId) {
		UnitId = unitId;
	}
	
}
