package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "currentcollateral")
public class CurrentCollateral {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer Id;
	
	@Column(name = "nasabah_id")
	private Long NasabahId;
	
	@Column(name = "jenisagunan_id")
	private String JenisAgunanId;
	
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

	public String getJenisAgunanId() {
		return JenisAgunanId;
	}

	public void setJenisAgunanId(String jenisAgunanId) {
		JenisAgunanId = jenisAgunanId;
	}

	public Integer getLastIndex() {
		return LastIndex;
	}

	public void setLastIndex(Integer lastIndex) {
		LastIndex = lastIndex;
	}
	
	

}
