package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "appunit")
public class AppUnit {
	
	@Id
	@Column(name = "unit_id")
	private String UnitId;
	
	@Column(name = "unit_name")
	private String UnitName;
	
	@Column(name = "kode_bpr")
	private String KodeBpr;
	
	@Column(name = "alamat")
	private String Alamat;
	
	@ManyToOne
	@JoinColumn(name = "propinsi_id", referencedColumnName = "provinsicode")
	private ParameterProvinsi PropinsiId;
	
	@ManyToOne
	@JoinColumn(name = "kota_id", referencedColumnName = "kotacode")
	private ParameterKotaKab KotaId;
	
	@Column(name = "is_active")
	private String IsActive;
	
	
	
	// Getter and Setter

	public String getUnitId() {
		return UnitId;
	}

	public void setUnitId(String unitId) {
		UnitId = unitId;
	}

	public String getUnitName() {
		return UnitName;
	}

	public void setUnitName(String unitName) {
		UnitName = unitName;
	}

	public String getKodeBpr() {
		return KodeBpr;
	}

	public void setKodeBpr(String kodeBpr) {
		KodeBpr = kodeBpr;
	}

	public String getAlamat() {
		return Alamat;
	}

	public void setAlamat(String alamat) {
		Alamat = alamat;
	}

	public ParameterProvinsi getPropinsiId() {
		return PropinsiId;
	}

	public void setPropinsiId(ParameterProvinsi propinsiId) {
		PropinsiId = propinsiId;
	}

	public ParameterKotaKab getKotaId() {
		return KotaId;
	}

	public void setKotaId(ParameterKotaKab kotaId) {
		KotaId = kotaId;
	}

	public String getIsActive() {
		return IsActive;
	}

	public void setIsActive(String isActive) {
		IsActive = isActive;
	}
	
	
	
	// Constructor
	
	public AppUnit() {
		
	}

}
