package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "parameteropenclose")
public class ParameterOpenClose {
	
	@Id
	@Column(name = "openclose_id")
	private String OpenCloseId;
	
	@Column(name = "openclose_name")
	private String OpenCloseName;
	
	

	public String getOpenCloseId() {
		return OpenCloseId;
	}

	public void setOpenCloseId(String openCloseId) {
		OpenCloseId = openCloseId;
	}

	public String getOpenCloseName() {
		return OpenCloseName;
	}

	public void setOpenCloseName(String openCloseName) {
		OpenCloseName = openCloseName;
	}
	
	

}
