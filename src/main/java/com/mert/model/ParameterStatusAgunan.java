package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "parameterstatusagunan")
public class ParameterStatusAgunan {
	
	@Id
	@Column(name = "statusagunan_id")
	private String StatusAgunanId;
	
	@Column(name = "statusagunan_name")
	private String StatusAgunanName;
	
	

	public String getStatusAgunanId() {
		return StatusAgunanId;
	}

	public void setStatusAgunanId(String statusAgunanId) {
		StatusAgunanId = statusAgunanId;
	}

	public String getStatusAgunanName() {
		return StatusAgunanName;
	}

	public void setStatusAgunanName(String statusAgunanName) {
		StatusAgunanName = statusAgunanName;
	}
	
	

}
