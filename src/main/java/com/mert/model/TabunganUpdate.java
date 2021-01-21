package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="rekeningtabungan")
public class TabunganUpdate {
	@Id
	@Column(name="no_rekg")
	private String no_rekg;

	//GetterandSetter
	
	public String getNo_rekg() {
		return no_rekg;
	}

	public void setNo_rekg(String no_rekg) {
		this.no_rekg = no_rekg;
	}
	
	//constructor
			public TabunganUpdate() { };
}
