package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "kodeeodunit")
public class KodeEodUnit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "kodeeodunit_id")
	private Integer Id;
	
	@Column(name = "kode_eod")
	private String KodeEod;
	
	@Column(name = "unit_id")
	private String UnitId;
	
	@Column(name = "rek_buku_besar")
	private String RekBukuBesar;
	
	@Column(name = "status")
	private String Status;
	
	// Getter and Setter

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getKodeEod() {
		return KodeEod;
	}

	public void setKodeEod(String kodeEod) {
		KodeEod = kodeEod;
	}

	public String getUnitId() {
		return UnitId;
	}

	public void setUnitId(String unitId) {
		UnitId = unitId;
	}

	public String getRekBukuBesar() {
		return RekBukuBesar;
	}

	public void setRekBukuBesar(String rekBukuBesar) {
		RekBukuBesar = rekBukuBesar;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}
	
	// Constructor
	
	public KodeEodUnit() {
		
	}

}
