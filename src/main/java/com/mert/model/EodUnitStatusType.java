package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "eodunitstatustype")
public class EodUnitStatusType {
	
	@Id
	@Column(name = "type_id")
	private String TypeId;
	
	@Column(name = "type_name")
	private String TypeName;
	
	

	public String getTypeId() {
		return TypeId;
	}

	public void setTypeId(String typeId) {
		TypeId = typeId;
	}

	public String getTypeName() {
		return TypeName;
	}

	public void setTypeName(String typeName) {
		TypeName = typeName;
	}
	
	

}
