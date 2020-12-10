package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipepecahan")
public class ParameterTipePecahan {
	
	@Id
	@Column(name = "pecahan_id")
	private String PecahanId;
	
	@Column(name = "pecahan_name")
	private String PecahanName;
	
	@Column(name = "valuta")
	private String Valuta;
	
	@Column(name = "nilai")
	private Double Nilai;
	
	@Column(name = "status")
	private String Status;
	
	

	public String getPecahanId() {
		return PecahanId;
	}

	public void setPecahanId(String pecahanId) {
		PecahanId = pecahanId;
	}

	public String getPecahanName() {
		return PecahanName;
	}

	public void setPecahanName(String pecahanName) {
		PecahanName = pecahanName;
	}

	public String getValuta() {
		return Valuta;
	}

	public void setValuta(String valuta) {
		Valuta = valuta;
	}

	public Double getNilai() {
		return Nilai;
	}

	public void setNilai(Double nilai) {
		Nilai = nilai;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}
	
	

}
