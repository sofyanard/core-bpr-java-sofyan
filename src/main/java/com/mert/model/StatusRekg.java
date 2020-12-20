package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="statusrekg")
public class StatusRekg {
	@Id
    @Column(name="status_rekening")
	private String status_rekening;
	
	@Column(name="deskripsi")
	private String deskripsi;
	
	@Column(name="status")
	private String status;
	
	//GetterandSetter


	public String getStatus_rekening() {
		return status_rekening;
	}

	public void setStatus_rekening(String status_rekening) {
		this.status_rekening = status_rekening;
	}

	public String getDeskripsi() {
		return deskripsi;
	}

	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	//constructor
		public StatusRekg() { };
}
