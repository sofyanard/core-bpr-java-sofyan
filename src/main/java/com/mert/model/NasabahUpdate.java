package com.mert.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "datanasabah")
public class NasabahUpdate {
	
	// Table Columns
	
	@Id
	@Column(name = "nonasabah")
	private Long nonasabah;
	
	@Column(name = "dateupdate")
	private Date dateupdate;
	
	
	
	// Getter and Setter

	public Long getNonasabah() {
		return nonasabah;
	}

	public void setNonasabah(Long nonasabah) {
		this.nonasabah = nonasabah;
	}
	
	public Date getDateupdate() {
		return dateupdate;
	}

	public void setDateupdate(Date dateupdate) {
		this.dateupdate = dateupdate;
	}
	
	
	
	// Constructor
	
	public NasabahUpdate() { }

}
