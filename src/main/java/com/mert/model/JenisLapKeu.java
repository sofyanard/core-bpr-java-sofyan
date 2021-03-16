package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "jenislapkeu")
public class JenisLapKeu {
	
	@Id
	@Column(name = "jenis_id")
	private String JenisId;
	
	@Column(name = "deskripsi")
	private String Deskripsi;
	
	@Column(name = "status")
	private String Status;
	
	

	public String getJenisId() {
		return JenisId;
	}

	public void setJenisId(String jenisId) {
		JenisId = jenisId;
	}

	public String getDeskripsi() {
		return Deskripsi;
	}

	public void setDeskripsi(String deskripsi) {
		Deskripsi = deskripsi;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}
	
}
