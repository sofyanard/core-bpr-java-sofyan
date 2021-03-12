package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "kodeeod")
public class KodeEod {
	
	@Id
	@Column(name = "kode_eod")
	private String KodeEod;
	
	@Column(name = "deskripsi")
	private String Deskripsi;
	
	@Column(name = "seq")
	private Integer Seq;
	
	@Column(name = "eom_only")
	private String EomOnly;
	
	@Column(name = "status")
	private String Status;
	
	// Getter and Setter

	public String getKodeEod() {
		return KodeEod;
	}

	public void setKodeEod(String kodeEod) {
		KodeEod = kodeEod;
	}

	public String getDeskripsi() {
		return Deskripsi;
	}

	public void setDeskripsi(String deskripsi) {
		Deskripsi = deskripsi;
	}

	public Integer getSeq() {
		return Seq;
	}

	public void setSeq(Integer seq) {
		Seq = seq;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}
	
	public String getEomOnly() {
		return EomOnly;
	}

	public void setEomOnly(String eomOnly) {
		EomOnly = eomOnly;
	}
	
	// Constructor
	
	public KodeEod() {
		
	}

}
