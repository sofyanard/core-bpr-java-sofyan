package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="kodebiaya", schema = "public")
public class ParameterKodeBiaya {

	@Id
    @Column(name="code")
	private String code;
	
	@Column(name="deskripsi")
	private String deskripsi;
	
	@Column(name = "buku_besar")
	private String BukuBesar;
	
	@Column(name="status")
	private String status;
	
	//GetterandSetter

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDeskripsi() {
		return deskripsi;
	}

	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}

	public String getBukuBesar() {
		return BukuBesar;
	}

	public void setBukuBesar(String bukuBesar) {
		BukuBesar = bukuBesar;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	//constructor
	public ParameterKodeBiaya() { }
}
