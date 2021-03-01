package com.mert.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "eodtanggal")
public class EodTanggal {
	
	@Id
	@Column(name = "eodtanggal_id")
	private String EodTanggalId;
	
	@Column(name = "eodtanggal_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date EodTanggalDate;
	
	// Getter and Setter

	public String getEodTanggalId() {
		return EodTanggalId;
	}

	public void setEodTanggalId(String eodTanggalId) {
		EodTanggalId = eodTanggalId;
	}

	public Date getEodTanggalDate() {
		return EodTanggalDate;
	}

	public void setEodTanggalDate(Date eodTanggalDate) {
		EodTanggalDate = eodTanggalDate;
	}
	
	// Constructor
	
	public EodTanggal() {
		
	}

}
