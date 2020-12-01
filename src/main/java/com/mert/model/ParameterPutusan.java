package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "parameterputusan")
public class ParameterPutusan {
	
	@Id
	@Column(name = "putusan_id")
	private String PutusanId;
	
	@Column(name = "putusan_name")
	private String PutusanName;
	
	

	public String getPutusanId() {
		return PutusanId;
	}

	public void setPutusanId(String putusanId) {
		PutusanId = putusanId;
	}

	public String getPutusanName() {
		return PutusanName;
	}

	public void setPutusanName(String putusanName) {
		PutusanName = putusanName;
	}
	
	
	
	public ParameterPutusan() {
		
	}

}
