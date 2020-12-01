package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "currentfacilityreference")
public class CurrentFacilityReference {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer Id;
	
	// YYYYMMDD
	@Column(name = "reference_date")
	private String ReferenceDate;
	
	@Column(name = "produk_id")
	private String ProdukId;
	
	@Column(name = "nasabah_id")
	private Long NasabahId;
	
	@Column(name = "unit_id")
	private String UnitId;
	
	@Column(name = "last_index")
	private Integer LastIndex;
	
	

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getReferenceDate() {
		return ReferenceDate;
	}

	public void setReferenceDate(String referenceDate) {
		ReferenceDate = referenceDate;
	}

	public String getProdukId() {
		return ProdukId;
	}

	public void setProdukId(String produkId) {
		ProdukId = produkId;
	}

	public Long getNasabahId() {
		return NasabahId;
	}

	public void setNasabahId(Long nasabahId) {
		NasabahId = nasabahId;
	}

	public String getUnitId() {
		return UnitId;
	}

	public void setUnitId(String unitId) {
		UnitId = unitId;
	}

	public Integer getLastIndex() {
		return LastIndex;
	}

	public void setLastIndex(Integer lastIndex) {
		LastIndex = lastIndex;
	}
	
	

}
