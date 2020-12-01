package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "parameteryesno")
public class ParameterYesNo {
	
	@Id
	@Column(name = "yesno_id")
	private String YesNoId;
	
	@Column(name = "yesno_name")
	private String YesNoName;
	
	

	public String getYesNoId() {
		return YesNoId;
	}

	public void setYesNoId(String yesNoId) {
		YesNoId = yesNoId;
	}

	public String getYesNoName() {
		return YesNoName;
	}

	public void setYesNoName(String yesNoName) {
		YesNoName = yesNoName;
	}
	
	
	
	public ParameterYesNo() {
		
	}

}
