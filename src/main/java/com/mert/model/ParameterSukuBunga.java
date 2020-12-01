package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sukubunga")
public class ParameterSukuBunga {
	
	@Id
	@Column(name = "code")
	private String Code;
	
	@Column(name = "desc")
	private String Desc;
	
	@Column(name = "rate")
	private Double Rate;
	
	@Column(name = "status")
	private String Status;
	
	@Column(name = "kategori")
	private String Kategori;
	
	@Column(name = "biojk")
	private String Biojk;
	
	

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public String getDesc() {
		return Desc;
	}

	public void setDesc(String desc) {
		Desc = desc;
	}

	public Double getRate() {
		return Rate;
	}

	public void setRate(Double rate) {
		Rate = rate;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getKategori() {
		return Kategori;
	}

	public void setKategori(String kategori) {
		Kategori = kategori;
	}

	public String getBiojk() {
		return Biojk;
	}

	public void setBiojk(String biojk) {
		Biojk = biojk;
	}
	
	
	
	public ParameterSukuBunga() {
		
	}

}
