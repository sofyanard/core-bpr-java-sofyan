package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "currentfacility")
public class CurrentFacility {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer Id;
	
	@Column(name = "nasabah_id")
	private Long NasabahId;
	
	@Column(name = "produk_id")
	private String ProdukId;
	
	@Column(name = "last_index")
	private Integer LastIndex;
	
	

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Long getNasabahId() {
		return NasabahId;
	}

	public void setNasabahId(Long nasabahId) {
		NasabahId = nasabahId;
	}

	public String getProdukId() {
		return ProdukId;
	}

	public void setProdukId(String produkId) {
		ProdukId = produkId;
	}

	public Integer getLastIndex() {
		return LastIndex;
	}

	public void setLastIndex(Integer lastIndex) {
		LastIndex = lastIndex;
	}
	
	

}
