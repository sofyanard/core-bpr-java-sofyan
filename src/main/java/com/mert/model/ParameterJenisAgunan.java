package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "jenisagunan")
public class ParameterJenisAgunan {
	
	@Id
	@Column(name = "jenisagunan_id")
	private String JenisAgunanId;
	
	@Column(name = "jenisagunan_name")
	private String JenisAgunanName;
	
	@Column(name = "biojk_category")
	private String BiojkCategory;
	
	@Column(name = "biojk_code")
	private String BiojkCode;
	
	@Column(name = "ppap_persen")
	private Double PpapPersen;
	
	@ManyToOne
	@JoinColumn(name = "ppap_base", referencedColumnName = "base_id")
	private ParameterJenisAgunanBase PpapBase;
	
	// Getter and Setter

	public String getJenisAgunanId() {
		return JenisAgunanId;
	}

	public void setJenisAgunanId(String jenisAgunanId) {
		JenisAgunanId = jenisAgunanId;
	}

	public String getJenisAgunanName() {
		return JenisAgunanName;
	}

	public void setJenisAgunanName(String jenisAgunanName) {
		JenisAgunanName = jenisAgunanName;
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
	
	public Double getPpapPersen() {
		return PpapPersen;
	}

	public void setPpapPersen(Double ppapPersen) {
		PpapPersen = ppapPersen;
	}

	public ParameterJenisAgunanBase getPpapBase() {
		return PpapBase;
	}

	public void setPpapBase(ParameterJenisAgunanBase ppapBase) {
		PpapBase = ppapBase;
	}

}
