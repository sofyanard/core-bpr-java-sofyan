package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "parameterhomestatus")
public class ParameterHomeStatus {
	
	@Id
	@Column(name = "homestatuscode")
	private String homestatuscode;
	
	@Column(name = "homestatusdesc")
	private String homestatusdesc;
	
	

	public String getHomestatuscode() {
		return homestatuscode;
	}

	public String getHomestatusdesc() {
		return homestatusdesc;
	}
	
	
	
	public ParameterHomeStatus() { }

}
