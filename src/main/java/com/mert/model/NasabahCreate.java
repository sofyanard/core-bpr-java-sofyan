package com.mert.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "datanasabah")
public class NasabahCreate {
	
	// Table Columns
	
	@Id
	@Column(name = "nonasabah")
	private Long nonasabah;
	
	@Column(name = "tipenasabah")
	private String tipenasabah;
	
	@Column(name = "datecreate")
	private Date datecreate;
	
	@Column(name = "unitcreate")
	private String unitcreate;
	
	
	
	// Getter and Setter

	public Long getNonasabah() {
		return nonasabah;
	}

	public void setNonasabah(Long nonasabah) {
		this.nonasabah = nonasabah;
	}
	
	public String getTipenasabah() {
		return tipenasabah;
	}

	public void setTipenasabah(String tipenasabah) {
		this.tipenasabah = tipenasabah;
	}

	public Date getDatecreate() {
		return datecreate;
	}

	public void setDatecreate(Date datecreate) {
		this.datecreate = datecreate;
	}
	
	public String getUnitcreate() {
		return unitcreate;
	}
	
	public void setUnitcreate(String unitcreate) {
		this.unitcreate = unitcreate;
	}
	
	
	
	// Constructor
	
	public NasabahCreate() { }

}
