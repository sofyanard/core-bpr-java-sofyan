package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "currentrekeningkredit")
public class CurrentRekeningKredit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer Id;
	
	@Column(name = "prefix")
	private String Prefix;
	
	@Column(name = "last_index")
	private Integer LastIndex;
	
	

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getPrefix() {
		return Prefix;
	}

	public void setPrefix(String prefix) {
		Prefix = prefix;
	}

	public Integer getLastIndex() {
		return LastIndex;
	}

	public void setLastIndex(Integer lastIndex) {
		LastIndex = lastIndex;
	}
	
	

}
