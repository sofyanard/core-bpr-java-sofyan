package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "currentcif")
public class NasabahCIF {
	
	// Table Columns
	
	@Id
	@Column(name = "prefix")
	private String prefix;
	
	@Column(name = "lastcif")
	private Long lastcif;

	
	
	// Getter and Setter
	
	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public Long getLastcif() {
		return lastcif;
	}

	public void setLastcif(Long lastcif) {
		this.lastcif = lastcif;
	}
	
	
	
	// Constructor
	
	public NasabahCIF() { }

}
